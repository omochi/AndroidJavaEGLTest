package com.omochimetaru.gles2test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class GLView extends SurfaceView implements SurfaceHolder.Callback {
    private EGL10 egl;
    private EGLDisplay eglDisplay;
    private EGLConfig eglConfig;
    private boolean surfaceCreated;
    private boolean eglSurfaceCreated;
    private EGLSurface eglSurface;

    public static interface Callback{
        void glViewEglSurfaceDidCreate();
        void glViewEglSurfaceWillDestroy();
    }
    public Callback callback;

    public GLView(Context context, EGL10 egl, EGLDisplay eglDisplay, EGLConfig eglConfig) {
        super(context);

        this.egl = egl;
        this.eglDisplay = eglDisplay;
        this.eglConfig = eglConfig;

        getHolder().setFormat(PixelFormat.RGB_888);
        getHolder().addCallback(this);

        surfaceCreated = false;
        eglSurfaceCreated = false;
    }

    public boolean isEglSurfaceCreated(){
        return eglSurfaceCreated;
    }

    public void eglSurfaceCreate() throws EGLErrorException{
        if(eglSurfaceCreated){
            throw new RuntimeException("already created");
        }
        eglSurface = egl.eglCreateWindowSurface(eglDisplay, eglConfig, getHolder(), null);
        if(eglSurface == EGLUtil.EGL_NO_SURFACE){
            new EGLErrorException("eglCreateWindowSurface", egl);
        }
        eglSurfaceCreated = true;
        if(callback!=null){ callback.glViewEglSurfaceDidCreate(); }
    }

    public void eglSurfaceDestroy() throws EGLErrorException{
        if(!eglSurfaceCreated){
            throw new RuntimeException("has not created");
        }
        if(callback!=null){ callback.glViewEglSurfaceWillDestroy(); }
        if(!egl.eglDestroySurface(eglDisplay, eglSurface)){
            throw new EGLErrorException("eglDestroySurface", egl);
        }
        eglSurfaceCreated = false;
    }

    public void eglMakeCurrent(EGLContext eglContext) throws EGLErrorException{
        if(!egl.eglMakeCurrent(eglDisplay, eglSurface, eglSurface, eglContext)){
            throw new EGLErrorException("eglMakeCurrent", egl);
        }
    }

    public void eglSwap() throws EGLErrorException{
        if(!egl.eglSwapBuffers(eglDisplay, eglSurface)){
            throw new EGLErrorException("eglSwapBuffers", egl);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i(EGLUtil.TAG, "surfaceCreated");
        surfaceCreated = true;

        if(!eglSurfaceCreated){
            try{
                eglSurfaceCreate();
            }catch(EGLErrorException e){ throw new RuntimeException(e); }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int w, int h) {
        Log.i(EGLUtil.TAG, String.format("surfaceChanged: format = %d, w = %d, h = %d",format, w, h));
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i(EGLUtil.TAG, "surfaceDestroyed");

        if(eglSurfaceCreated){
            try{
                eglSurfaceDestroy();
            }catch(EGLErrorException e){ throw new RuntimeException(e); }
        }

        surfaceCreated = false;
    }


}
