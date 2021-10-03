package com.mtlteam.openhelperlibmain;

import android.app.Activity;
import android.util.Log;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

public class ReviewManager {

    public static void inAppReview(Activity activity) {
        com.google.android.play.core.review.ReviewManager manager = ReviewManagerFactory.create(activity);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                Log.d("TAG", "inAppReview: We can get the ReviewInfo object!");
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                flow.addOnCompleteListener(task2 -> {
                    Log.d("TAG", "inAppReview: The flow has finished!");
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                });
            }  // There was some problem, continue regardless of the result.

        });
    }

}
