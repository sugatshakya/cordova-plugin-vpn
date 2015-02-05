package org.aquto.cordova.vpn;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.strongswan.android.logic.VpnStateService;

public class CordovaVPNStateListener implements VpnStateService.VpnStateListener {
    private CallbackContext callbackContext;
    private VpnStateService mService;

    public CordovaVPNStateListener(CallbackContext _callbackContext, VpnStateService _mService) {
        callbackContext = _callbackContext;
        mService = _mService;
    }

    @Override
    public void stateChanged() {
        VpnStateService.ErrorState eState = mService.getErrorState();
        VpnStateService.State newState = mService.getState();
        PluginResult pr;
        if(eState != VpnStateService.ErrorState.NO_ERROR) {
            pr = new PluginResult(PluginResult.Status.ERROR, eState.toString());
        } else {
            pr = new PluginResult(PluginResult.Status.OK, newState.toString());
            pr.setKeepCallback(true);
        }
        callbackContext.sendPluginResult(pr);
    }
}