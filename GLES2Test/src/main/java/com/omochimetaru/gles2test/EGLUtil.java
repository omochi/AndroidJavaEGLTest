package com.omochimetaru.gles2test;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/**
 * Created by omochi on 2013/12/31.
 */
public class EGLUtil {

    public static final String TAG = "gles2test";

    public static final int EGL_DEFAULT_DISPLAY = 0;
    public static EGLContext EGL_NO_CONTEXT = EGL10.EGL_NO_CONTEXT;
    public static EGLDisplay EGL_NO_DISPLAY = EGL10.EGL_NO_DISPLAY;
    public static EGLSurface EGL_NO_SURFACE = EGL10.EGL_NO_SURFACE;
    public static final int EGL_FALSE = 0;
    public static final int EGL_TRUE = 1;
    public static final int EGL_SUCCESS = 12288;
    public static final int EGL_NOT_INITIALIZED = 12289;
    public static final int EGL_BAD_ACCESS = 12290;
    public static final int EGL_BAD_ALLOC = 12291;
    public static final int EGL_BAD_ATTRIBUTE = 12292;
    public static final int EGL_BAD_CONFIG = 12293;
    public static final int EGL_BAD_CONTEXT = 12294;
    public static final int EGL_BAD_CURRENT_SURFACE = 12295;
    public static final int EGL_BAD_DISPLAY = 12296;
    public static final int EGL_BAD_MATCH = 12297;
    public static final int EGL_BAD_NATIVE_PIXMAP = 12298;
    public static final int EGL_BAD_NATIVE_WINDOW = 12299;
    public static final int EGL_BAD_PARAMETER = 12300;
    public static final int EGL_BAD_SURFACE = 12301;
    public static final int EGL_CONTEXT_LOST = 12302;
    public static final int EGL_BUFFER_SIZE = 12320;
    public static final int EGL_ALPHA_SIZE = 12321;
    public static final int EGL_BLUE_SIZE = 12322;
    public static final int EGL_GREEN_SIZE = 12323;
    public static final int EGL_RED_SIZE = 12324;
    public static final int EGL_DEPTH_SIZE = 12325;
    public static final int EGL_STENCIL_SIZE = 12326;
    public static final int EGL_CONFIG_CAVEAT = 12327;
    public static final int EGL_CONFIG_ID = 12328;
    public static final int EGL_LEVEL = 12329;
    public static final int EGL_MAX_PBUFFER_HEIGHT = 12330;
    public static final int EGL_MAX_PBUFFER_PIXELS = 12331;
    public static final int EGL_MAX_PBUFFER_WIDTH = 12332;
    public static final int EGL_NATIVE_RENDERABLE = 12333;
    public static final int EGL_NATIVE_VISUAL_ID = 12334;
    public static final int EGL_NATIVE_VISUAL_TYPE = 12335;
    public static final int EGL_SAMPLES = 12337;
    public static final int EGL_SAMPLE_BUFFERS = 12338;
    public static final int EGL_SURFACE_TYPE = 12339;
    public static final int EGL_TRANSPARENT_TYPE = 12340;
    public static final int EGL_TRANSPARENT_BLUE_VALUE = 12341;
    public static final int EGL_TRANSPARENT_GREEN_VALUE = 12342;
    public static final int EGL_TRANSPARENT_RED_VALUE = 12343;
    public static final int EGL_NONE = 12344;
    public static final int EGL_BIND_TO_TEXTURE_RGB = 12345;
    public static final int EGL_BIND_TO_TEXTURE_RGBA = 12346;
    public static final int EGL_MIN_SWAP_INTERVAL = 12347;
    public static final int EGL_MAX_SWAP_INTERVAL = 12348;
    public static final int EGL_LUMINANCE_SIZE = 12349;
    public static final int EGL_ALPHA_MASK_SIZE = 12350;
    public static final int EGL_COLOR_BUFFER_TYPE = 12351;
    public static final int EGL_RENDERABLE_TYPE = 12352;
    public static final int EGL_MATCH_NATIVE_PIXMAP = 12353;
    public static final int EGL_CONFORMANT = 12354;
    public static final int EGL_SLOW_CONFIG = 12368;
    public static final int EGL_NON_CONFORMANT_CONFIG = 12369;
    public static final int EGL_TRANSPARENT_RGB = 12370;
    public static final int EGL_RGB_BUFFER = 12430;
    public static final int EGL_LUMINANCE_BUFFER = 12431;
    public static final int EGL_NO_TEXTURE = 12380;
    public static final int EGL_TEXTURE_RGB = 12381;
    public static final int EGL_TEXTURE_RGBA = 12382;
    public static final int EGL_TEXTURE_2D = 12383;
    public static final int EGL_PBUFFER_BIT = 1;
    public static final int EGL_PIXMAP_BIT = 2;
    public static final int EGL_WINDOW_BIT = 4;
    public static final int EGL_VG_COLORSPACE_LINEAR_BIT = 32;
    public static final int EGL_VG_ALPHA_FORMAT_PRE_BIT = 64;
    public static final int EGL_MULTISAMPLE_RESOLVE_BOX_BIT = 512;
    public static final int EGL_SWAP_BEHAVIOR_PRESERVED_BIT = 1024;
    public static final int EGL_OPENGL_ES_BIT = 1;
    public static final int EGL_OPENVG_BIT = 2;
    public static final int EGL_OPENGL_ES2_BIT = 4;
    public static final int EGL_OPENGL_BIT = 8;
    public static final int EGL_VENDOR = 12371;
    public static final int EGL_VERSION = 12372;
    public static final int EGL_EXTENSIONS = 12373;
    public static final int EGL_CLIENT_APIS = 12429;
    public static final int EGL_HEIGHT = 12374;
    public static final int EGL_WIDTH = 12375;
    public static final int EGL_LARGEST_PBUFFER = 12376;
    public static final int EGL_TEXTURE_FORMAT = 12416;
    public static final int EGL_TEXTURE_TARGET = 12417;
    public static final int EGL_MIPMAP_TEXTURE = 12418;
    public static final int EGL_MIPMAP_LEVEL = 12419;
    public static final int EGL_RENDER_BUFFER = 12422;
    public static final int EGL_VG_COLORSPACE = 12423;
    public static final int EGL_VG_ALPHA_FORMAT = 12424;
    public static final int EGL_HORIZONTAL_RESOLUTION = 12432;
    public static final int EGL_VERTICAL_RESOLUTION = 12433;
    public static final int EGL_PIXEL_ASPECT_RATIO = 12434;
    public static final int EGL_SWAP_BEHAVIOR = 12435;
    public static final int EGL_MULTISAMPLE_RESOLVE = 12441;
    public static final int EGL_BACK_BUFFER = 12420;
    public static final int EGL_SINGLE_BUFFER = 12421;
    public static final int EGL_VG_COLORSPACE_sRGB = 12425;
    public static final int EGL_VG_COLORSPACE_LINEAR = 12426;
    public static final int EGL_VG_ALPHA_FORMAT_NONPRE = 12427;
    public static final int EGL_VG_ALPHA_FORMAT_PRE = 12428;
    public static final int EGL_DISPLAY_SCALING = 10000;
    public static final int EGL_BUFFER_PRESERVED = 12436;
    public static final int EGL_BUFFER_DESTROYED = 12437;
    public static final int EGL_OPENVG_IMAGE = 12438;
    public static final int EGL_CONTEXT_CLIENT_TYPE = 12439;
    public static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    public static final int EGL_MULTISAMPLE_RESOLVE_DEFAULT = 12442;
    public static final int EGL_MULTISAMPLE_RESOLVE_BOX = 12443;
    public static final int EGL_OPENGL_ES_API = 12448;
    public static final int EGL_OPENVG_API = 12449;
    public static final int EGL_OPENGL_API = 12450;
    public static final int EGL_DRAW = 12377;
    public static final int EGL_READ = 12378;
    public static final int EGL_CORE_NATIVE_ENGINE = 12379;

    public static final int[] configKeys = {
            EGL_BUFFER_SIZE,
            EGL_RED_SIZE,
            EGL_GREEN_SIZE,
            EGL_BLUE_SIZE,
            EGL_LUMINANCE_SIZE,
            EGL_ALPHA_SIZE,
            EGL_ALPHA_MASK_SIZE,
            EGL_BIND_TO_TEXTURE_RGB,
            EGL_BIND_TO_TEXTURE_RGBA,
            EGL_COLOR_BUFFER_TYPE,
            EGL_CONFIG_CAVEAT,
            EGL_CONFIG_ID,
            EGL_CONFORMANT,
            EGL_DEPTH_SIZE,
            EGL_LEVEL,
            EGL_MAX_PBUFFER_WIDTH,
            EGL_MAX_PBUFFER_HEIGHT,
            EGL_MAX_PBUFFER_PIXELS,
            EGL_MAX_SWAP_INTERVAL,
            EGL_MIN_SWAP_INTERVAL,
            EGL_NATIVE_RENDERABLE,
            EGL_NATIVE_VISUAL_ID,
            EGL_NATIVE_VISUAL_TYPE,
            EGL_RENDERABLE_TYPE,
            EGL_SAMPLE_BUFFERS,
            EGL_SAMPLES,
            EGL_STENCIL_SIZE,
            EGL_SURFACE_TYPE,
            EGL_TRANSPARENT_TYPE,
            EGL_TRANSPARENT_RED_VALUE,
            EGL_TRANSPARENT_GREEN_VALUE,
            EGL_TRANSPARENT_BLUE_VALUE
    };


    public static String configKeyToString(int key){
        switch(key){
            case EGL_BUFFER_SIZE: return "BUFFER_SIZE";
            case EGL_RED_SIZE: return "RED_SIZE";
            case EGL_GREEN_SIZE: return "GREEN_SIZE";
            case EGL_BLUE_SIZE: return "BLUE_SIZE";
            case EGL_LUMINANCE_SIZE: return "LUMINANCE_SIZE";
            case EGL_ALPHA_SIZE: return "ALPHA_SIZE";
            case EGL_ALPHA_MASK_SIZE: return "ALPHA_MASK_SIZE";
            case EGL_BIND_TO_TEXTURE_RGB: return "BIND_TO_TEXTURE_RGB";
            case EGL_BIND_TO_TEXTURE_RGBA: return "BIND_TO_TEXTURE_RGBA";
            case EGL_COLOR_BUFFER_TYPE: return "COLOR_BUFFER_TYPE";
            case EGL_CONFIG_CAVEAT: return "CONFIG_CAVEAT";
            case EGL_CONFIG_ID: return "CONFIG_ID";
            case EGL_CONFORMANT: return "CONFORMANT";
            case EGL_DEPTH_SIZE: return "DEPTH_SIZE";
            case EGL_LEVEL: return "LEVEL";
            case EGL_MAX_PBUFFER_WIDTH: return "MAX_PBUFFER_WIDTH";
            case EGL_MAX_PBUFFER_HEIGHT: return "MAX_PBUFFER_HEIGHT";
            case EGL_MAX_PBUFFER_PIXELS: return "MAX_PBUFFER_PIXELS";
            case EGL_MAX_SWAP_INTERVAL: return "MAX_SWAP_INTERVAL";
            case EGL_MIN_SWAP_INTERVAL: return "MIN_SWAP_INTERVAL";
            case EGL_NATIVE_RENDERABLE: return "NATIVE_RENDERABLE";
            case EGL_NATIVE_VISUAL_ID: return "NATIVE_VISUAL_ID";
            case EGL_NATIVE_VISUAL_TYPE: return "NATIVE_VISUAL_TYPE";
            case EGL_RENDERABLE_TYPE: return "RENDERABLE_TYPE";
            case EGL_SAMPLE_BUFFERS: return "SAMPLE_BUFFERS";
            case EGL_SAMPLES: return "SAMPLES";
            case EGL_STENCIL_SIZE: return "STENCIL_SIZE";
            case EGL_SURFACE_TYPE: return "SURFACE_TYPE";
            case EGL_TRANSPARENT_TYPE: return "TRANSPARENT_TYPE";
            case EGL_TRANSPARENT_RED_VALUE: return "TRANSPARENT_RED_VALUE";
            case EGL_TRANSPARENT_GREEN_VALUE: return "TRANSPARENT_GREEN_VALUE";
            case EGL_TRANSPARENT_BLUE_VALUE: return "TRANSPARENT_BLUE_VALUE";
            default: return String.format("(%d)", key);
        }
    }

    public static int configGetAttrib(EGL10 egl, EGLDisplay display, EGLConfig config, int key)
            throws EGLErrorException
    {
        int value[] = new int[1];
        if(!egl.eglGetConfigAttrib(display, config, key, value)){
            int code = egl.eglGetError();
            if(code == EGL_BAD_ATTRIBUTE){
                return -1;
            }else{
                throw new EGLErrorException(String.format("eglGetConfigAttrib(%s)", key), code);
            }
        }
        return value[0];
    }

    public static String configToString(EGL10 egl, EGLDisplay display, EGLConfig config) throws EGLErrorException {
        String str = "";
        for(int i = 0; i < configKeys.length; i++){
            int key = configKeys[i];
            if(i > 0){
                str += ", ";
            }
            str += String.format("%s: %d",
                    configKeyToString(key),
                    configGetAttrib(egl, display, config, key));
        }
        return str;
    }

}
