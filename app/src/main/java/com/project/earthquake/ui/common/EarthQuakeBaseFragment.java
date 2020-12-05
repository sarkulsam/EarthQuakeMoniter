package com.project.earthquake.ui.common;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.project.earthquake.repository.IEarthQuakeRepository;

import androidx.fragment.app.Fragment;

/**
 * This class will provide base functionality for all fragment.
 */
public class EarthQuakeBaseFragment extends Fragment {
    protected ProgressDialog progressDialog;

    protected EarthQuakeApplication getEarthQuakeApplication() {
        EarthQuakeApplication application = (EarthQuakeApplication) getActivity().getApplication();
        return application;
    }

    protected IEarthQuakeRepository getEarthQuakeRepository() {
        IEarthQuakeRepository iEarthQuakeRepository = getEarthQuakeApplication()
                .getAppComponent()
                .earthQuakeRepository();

        return iEarthQuakeRepository;
    }

    protected void showProgressDialog(String title, String msg) {
        if(getActivity() == null || !isAdded() || isRemoving()) return;

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(title);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    protected void cancelProgressDialog() {
        if(progressDialog == null || !progressDialog.isShowing()) return;

        progressDialog.cancel();
    }

    protected void showAlertDialog(String title,
                                   String msg,
                                   String postiveBtnText,
                                   DialogInterface.OnClickListener postiveBtnOnclick,
                                   String negativeBtnText,
                                   DialogInterface.OnClickListener negativeBtnOnclick
    ) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(postiveBtnText, postiveBtnOnclick)
                .setNegativeButton(negativeBtnText, negativeBtnOnclick)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
