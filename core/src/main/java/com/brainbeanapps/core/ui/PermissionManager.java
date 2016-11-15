package com.brainbeanapps.core.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.brainbeanapps.core.BaseException;
import com.brainbeanapps.core.mvp.MvpView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.subjects.PublishSubject;

import static android.support.v4.content.ContextCompat.*;


@Singleton
public class PermissionManager {

    private PublishSubject<Integer> permissionResults;
    private int                     requestCodeCount;

    @Inject
    public PermissionManager() {
        permissionResults = PublishSubject.create();
    }

    /**
     * Reactive implementation for requesting run time permissions
     *
     * @param permissionsArr - array of {@link String} permissions
     * @param views - current view {@link MvpView}
     * @return Observable.onNext() return {@link PermissionObtained} only if all permissions granted
     */
    public Observable<PermissionObtained> requestPermissions(String[] permissionsArr, Observable<MvpView> views) {

        return views.switchMap(view -> {

            List<String> permissions    = Arrays.asList(permissionsArr);
            List<String> misPermissions = checkPermissions(permissions, view.getContext());

            if (!misPermissions.isEmpty()) {

                int      requestCode = getRequestCodeCount();
                String[] misPermArr  = misPermissions.toArray(new String[misPermissions.size()]);

                view.requestPermission(misPermArr, requestCode);

                return permissionResults.switchMap(receivedRequestCode -> {

                    List<String> deniedPermissions = checkPermissions(permissions, view.getContext());

                    if (deniedPermissions.isEmpty())
                        return Observable.just(new PermissionObtained());

                    else if (receivedRequestCode == requestCode)
                        return Observable.error(new MissingPermissionException(deniedPermissions));

                    else return Observable.empty();
                });

            } else return Observable.just(new PermissionObtained());

        }).take(1);
    }

    /**
     * Setting {@link #requestCodeCount} after calling {@link #requestPermissions(String[], Observable)}
     *
     * @param requestCode the code that was automatically created on request
     */
    public void onRequestPermissionsResult(int requestCode) {
        permissionResults.onNext(requestCode);
    }

    private List<String> checkPermissions(List<String> permissions, Context context) {

        List<String> requiredPermissions = new ArrayList<>();

        for (String permission : permissions)
            if (checkIfRequired(permission, context)) requiredPermissions.add(permission);

        return requiredPermissions;
    }

    private boolean checkIfRequired(String permission, Context context) {
        return checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
    }

    private int getRequestCodeCount() {
        return requestCodeCount++;
    }

    public class PermissionObtained {
    }

    public class MissingPermissionException extends BaseException {

        private List<String> permissions;

        public MissingPermissionException(List<String> permissions) {
            this.permissions = permissions;
        }

        @Override
        public String getMessage() {
            return "Permissions denied " + TextUtils.join(", ", permissions);
        }
    }
}