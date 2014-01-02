package com.omochimetaru.gles2test;

import android.util.Log;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/**
 * Created by omochi on 2013/12/31.
 */
public class EGLBitNumConfigChooser implements EGLConfigChooser {

    private int redBitNum;
    private int greenBitNum;
    private int blueBitNum;
    private int alphaBitNum;
    private int depthBitNum;
    private int stencilBitNum;

    public EGLBitNumConfigChooser(int redBitNum, int greenBitNum, int blueBitNum, int alphaBitNum,
                                   int depthBitNum, int stencilBitNum){
        this.redBitNum = redBitNum;
        this.greenBitNum = greenBitNum;
        this.blueBitNum = blueBitNum;
        this.alphaBitNum = alphaBitNum;
        this.depthBitNum = depthBitNum;
        this.stencilBitNum = stencilBitNum;
    }

    @Override
    public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display){
        try{
            int num[] = new int[1];
            if(!egl.eglGetConfigs(display, null, 0, num)){
                throw new EGLErrorException("GetConfigs: get num", egl);
            }
            EGLConfig[] configList = new EGLConfig[num[0]];
            if(!egl.eglGetConfigs(display, configList, num[0], num)){
                throw new EGLErrorException("GetConfigs: get configs", egl);
            }
            return chooseConfig(egl, display, configList);
        }catch(EGLErrorException e){
            throw new RuntimeException(e);
        }
    }

    private EGLConfig chooseConfig(EGL10 egl, EGLDisplay display, EGLConfig configList[]) throws EGLErrorException {
        int bestIndex = -1;
        int bestDepthBitNum = 0;
        int bestStencilBitNum = 0;

        Log.i(EGLUtil.TAG, String.format("config num: %d", configList.length) );

        for(int i = 0; i < configList.length; i++){
            EGLConfig config = configList[i];

            //Log.i(EGLUtil.TAG, String.format("config[%d] = %s",i,EGLUtil.configToString(egl, display, config)));

            int redBitNum = EGLUtil.configGetAttrib(egl, display, config, EGL10.EGL_RED_SIZE);
            if(redBitNum != this.redBitNum){ continue; }
            int greenBitNum = EGLUtil.configGetAttrib(egl, display, config, EGL10.EGL_GREEN_SIZE);
            if(greenBitNum != this.greenBitNum){ continue; }
            int blueBitNum = EGLUtil.configGetAttrib(egl, display, config, EGL10.EGL_BLUE_SIZE);
            if(blueBitNum != this.blueBitNum){ continue; }
            int alphaBitNum = EGLUtil.configGetAttrib(egl, display, config, EGL10.EGL_ALPHA_SIZE);
            if(alphaBitNum != this.alphaBitNum){ continue; }

            int depthBitNum = EGLUtil.configGetAttrib(egl, display, config, EGL10.EGL_DEPTH_SIZE);
            if(depthBitNum < this.depthBitNum){ continue; }
            int stencilBitNum = EGLUtil.configGetAttrib(egl, display, config, EGL10.EGL_STENCIL_SIZE);
            if(stencilBitNum < this.stencilBitNum){ continue; }

            if(bestIndex == -1 ||
                    (bestDepthBitNum <= depthBitNum ) ||
                    (bestDepthBitNum == depthBitNum && bestStencilBitNum <= stencilBitNum))
            {
                bestIndex = i;
                bestDepthBitNum = depthBitNum;
                bestStencilBitNum = stencilBitNum;
            }
        }

        if(bestIndex != -1){
            EGLConfig bestConfig = configList[bestIndex];

            Log.i(EGLUtil.TAG, String.format("bestConfig = %s",EGLUtil.configToString(egl, display, bestConfig)));

            return configList[bestIndex];
        }
        return null;
    }


}
