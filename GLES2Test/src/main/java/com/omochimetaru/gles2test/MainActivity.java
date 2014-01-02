package com.omochimetaru.gles2test;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.opengl.GLES20;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.FrameLayout;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

public class MainActivity extends Activity {
    private static final int MP = ViewGroup.LayoutParams.MATCH_PARENT;

    private FrameLayout rootLayout;

    private GLView glView;

    private EGL10 egl = (EGL10)EGLContext.getEGL();
    private EGLDisplay eglDisplay;
    private EGLContext eglContext;

    private Timer timer;
    private boolean updateRunning;
    private LoopThread renderThread;
    private boolean renderRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            eglDisplay = egl.eglGetDisplay(egl.EGL_DEFAULT_DISPLAY);
            if(eglDisplay == EGLUtil.EGL_NO_DISPLAY){
                throw new EGLErrorException("GetDisplay(DEFAULT)", egl);
            }
            int[] eglVer = new int[2];
            if(!egl.eglInitialize(eglDisplay, eglVer)){
                throw new EGLErrorException("Initialize", egl);
            }
            Log.i(EGLUtil.TAG, String.format("egl display: %s, ver: %d.%d",eglDisplay,eglVer[0],eglVer[1]));

            EGLConfigChooser configChooser = new EGLBitNumConfigChooser(8,8,8,8,16,8);
            EGLConfig eglConfig = configChooser.chooseConfig(egl, eglDisplay);
            if(eglConfig == null){
                throw new RuntimeException("config not found");
            }

            eglContext = egl.eglCreateContext(eglDisplay, eglConfig, egl.EGL_NO_CONTEXT,
                    new int[]{
                            EGLUtil.EGL_CONTEXT_CLIENT_VERSION, 2,
                            EGLUtil.EGL_NONE
                    });
            if(eglContext == EGLUtil.EGL_NO_CONTEXT){
                throw new RuntimeException(new EGLErrorException("CreateContext", egl));
            }
            Log.i(EGLUtil.TAG, String.format("egl context: %s",eglContext));

            rootLayout = new FrameLayout(this);
            rootLayout.setLayoutParams(new ViewGroup.LayoutParams(MP, MP));
            setContentView(rootLayout);

            glView = new GLView(this, egl, eglDisplay, eglConfig);
            glView.setLayoutParams(new FrameLayout.LayoutParams(MP, MP));
            rootLayout.addView(glView);

            renderThread = new LoopThread();
            renderThread.start();

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    signalUpdateTime();
                }
            }, 33, 33);

        }catch(EGLErrorException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onDestroy(){
        try{
            timer.cancel();
            timer = null;

            renderThread.postQuit();
            while(renderThread != null){
                try {
                    renderThread.join();
                    renderThread = null;
                } catch (InterruptedException e) { continue; }
            }

            if(glView.isEglSurfaceCreated()){
                glView.eglSurfaceDestroy();
            }
            ((ViewGroup)glView.getParent()).removeView(glView);
            glView = null;

            if(!egl.eglDestroyContext(eglDisplay, eglContext)){
                new EGLErrorException("DestroyContext",egl);
            }
            eglContext = null;
            Log.i(EGLUtil.TAG, "egl context destroyed");

            if(!egl.eglTerminate(eglDisplay)){
                new EGLErrorException("Terminate", egl);
            }
            eglDisplay = null;
            Log.i(EGLUtil.TAG, "egl terminated");
        }catch(EGLErrorException e){
            throw new RuntimeException(e);
        }
        super.onDestroy();
    }

    public synchronized void signalUpdateTime(){
        if(!updateRunning){
            requestUpdate();
        }
    }

    public synchronized void requestUpdate(){
        updateRunning = true;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
    }

    public synchronized void requestRender(){
        renderRunning = true;
        renderThread.post(new Runnable() {
            @Override
            public void run() {
                try{
                    render();
                }catch(EGLErrorException e){
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //main thread
    private void update(){

        if(timer!=null){

            if(glView.isEglSurfaceCreated()){
                Log.i(EGLUtil.TAG, "requestRender");
                requestRender();
                synchronized (this){
                    while(renderRunning){
                        try { wait(); }
                        catch (InterruptedException e) { continue; }
                    }
                }
            }else{
                Log.i(EGLUtil.TAG, "render skip");
            }

        }
        synchronized (this){
            updateRunning = false;
            notifyAll();
        }
    }

    //render thread
    private void render() throws EGLErrorException {

        glView.eglMakeCurrent(eglContext);

        GLES20.glClearColor(1,0,0,1);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        glView.eglSwap();

        synchronized (this){
            renderRunning = false;
            this.notifyAll();
        }
    }

}
