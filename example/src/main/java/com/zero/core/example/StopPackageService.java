package com.zero.core.example;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;

import com.zero.core.AppEnv;
import com.zero.core.CoreServiceManager;
import com.zero.core.Service;

/**
 * 测试例子
 * 
 * @author chaopei
 *
 */
public class StopPackageService extends IStopPackageService.Stub {

    private static final boolean DEBUG = AppEnv.DEBUG;

    private static final String TAG = StopPackageService.class.getSimpleName();

    public static final int SERVICE_ID = 0;

    private static StopPackageService instance;

    final public static Service INSTALLER = new Service() {

        @Override
        public int getServiceId() {
            return SERVICE_ID;
        }

        @Override
        public IBinder getService() {
            return StopPackageService.getService();
        }

        @Override
        public IInterface asInterface(IBinder binder) {
            return IStopPackageService.Stub.asInterface(binder);
        }
    };

    private StopPackageService() {
    }

    /**
     * 【Server进程】install服务时使用
     * 
     * @return
     */
    public static IBinder getService() {
        INSTALLER.ensureInRightProcess();
        if (null == instance) {
            instance = new StopPackageService();
        }
        return instance;
    }

    @Override
    public void killNoWait(String name) throws RemoteException {
        if (DEBUG) {
            Log.d(TAG, "[killNoWait]：" + name);
        }
    }

    @Override
    public void killWait(String name) throws RemoteException {
        if (DEBUG) {
            Log.d(TAG, "[killWait]：" + name);
        }

    }

    @Override
    public void killAllNoWait(String[] names) throws RemoteException {
        if (DEBUG) {
            Log.d(TAG, "[killAllNoWait]：" + names.length);
        }

    }

    @Override
    public void killAllWait(String[] names) throws RemoteException {
        if (DEBUG) {
            Log.d(TAG, "[killAllWait]：" + names.length);
        }

    }

    @Override
    public void killSysNoWait() throws RemoteException {
        if (DEBUG) {
            Log.d(TAG, "[killSysNoWait]");
        }
        IStopPackageService iui = (IStopPackageService) CoreServiceManager.getService(StopPackageUI.SERVICE_ID);
        try {
            iui.killSysNoWait();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void killSysWait() throws RemoteException {
        if (DEBUG) {
            Log.d(TAG, "[killSysWait]", new Throwable());
        }
//        int a=0;a=1/a;
        throw new NullPointerException();
    }

    @Override
    public void killUserNoWait() throws RemoteException {
        if (DEBUG) {
            Log.d(TAG, "[killUserNoWait]");
        }

    }

    @Override
    public void killUserWait(Surface a) throws RemoteException {
        if (DEBUG) {
            Log.d(TAG, "[killUserWait]");
        }
    }

}
