package com.brainbeanapps.core.net;

import com.brainbeanapps.core.net.model.BaseResponse;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by Rosty on 10/19/2016.
 */

public class MapResponseHelper {

    /**
     * Transform {@link Response} to {@link BaseResponse} or return {@link Exception}
     *
     * @param <T> type of {@link BaseResponse}
     * @return
     */
    public static <T extends BaseResponse> Observable.Transformer<Response<T>, T> convertResponse() {

        return responseObservable -> responseObservable
                .switchMap(response -> {
                    if (response.isSuccessful()) return Observable.just(response.body());
                    return Observable.error(new NetworkException(response.code()));
                });
    }
}
