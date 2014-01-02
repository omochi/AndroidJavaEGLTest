package com.omochimetaru.gles2test;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/**
 * Created by omochi on 2013/12/31.
 */
public interface EGLConfigChooser {
    public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display);
}
