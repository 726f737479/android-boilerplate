package com.brainbeanapps.core.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public final class NetworkUtil {

    private NetworkUtil(){}

    /**
     * Method that check if device have network connection
     *
     * @param context {@link Context}
     * @return true if devise have internet connection, false - no network connection
     */
    public static boolean isNetworkConnected(Context context){

        NetworkStatus networkStatus = getNetworkStatus(context);
        return networkStatus.isMobileConnected || networkStatus.isWiFiConnected;
    }

    /**
     * Method that check if device have wifi network connection
     *
     * @param context {@link Context}
     * @return true if devise have wifi connection, false - no wifi connection
     */
    public static boolean isWiFiConnected(Context context){

        return getNetworkStatus(context).isWiFiConnected;
    }

    /**
     * Method create and return {@link NetworkStatus} instance
     *
     * @param context {@link Context}
     * @return {@link NetworkStatus}
     */
    public static NetworkStatus getNetworkStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {

            return new NetworkStatus(
                    activeNetwork.getType() == ConnectivityManager.TYPE_WIFI,
                    activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE);

        } else return new NetworkStatus(false, false);
    }

    /**
     * Reports the type of network to which the info in this {@code NetworkInfo} pertains.
     *
     * @return one of {@link ConnectivityManager#TYPE_MOBILE}, {@link
     * ConnectivityManager#TYPE_WIFI}, {@link ConnectivityManager#TYPE_WIMAX}, {@link
     * ConnectivityManager#TYPE_ETHERNET},  {@link ConnectivityManager#TYPE_BLUETOOTH}, or other
     * types defined by {@link ConnectivityManager}
     */
    public static int getCurrentDataType(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return  activeNetwork.getType();
    }

    public static class NetworkStatus{

        private boolean isWiFiConnected;
        private boolean isMobileConnected;

        public NetworkStatus(boolean isWiFiConnected, boolean isMobileConnected) {

            this.isWiFiConnected = isWiFiConnected;
            this.isMobileConnected = isMobileConnected;
        }

        /**
         * @return true if mobile network connected, false - mobile network don't connected
         */
        public boolean isMobileConnected() {
            return isMobileConnected;
        }

        /**
         * @return true if wifi network connected, false - wifi internet don't connected
         */
        public boolean isWiFiConnected() {
            return isWiFiConnected;
        }

        /**
         * @return true if some network connected, false - no network connected
         */
        public boolean isConnected(){
            return isWiFiConnected || isMobileConnected;
        }
    }
}