package org.tomasdavid.vehicleroutingproblem.components;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import org.tomasdavid.vehicleroutingproblem.MainActivity;
import org.tomasdavid.vehicleroutingproblem.R;
import org.tomasdavid.vehicleroutingproblem.fragments.VrpFragment;

/**
 * Stop solver dialog. Displays when solver running and user clicks on the back button.
 *
 * @author Tomas David
 */
public class StopSolverDialog extends DialogFragment implements OnClickListener {

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.solver_running_message)
                .setPositiveButton(R.string.button_ok, this)
                .setNegativeButton(R.string.button_cancel, this);
        return builder.create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {

            // solver is stopped, nav. drawer is hidden and list fragment is displayed
            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.activity_main);
            if (fragment instanceof VrpFragment) {
                ((Toolbar) getActivity().findViewById(R.id.toolbar)).setNavigationIcon(null);
                (((VrpFragment) fragment).getVrpSolverTask()).stopTask();
                ((MainActivity) getActivity()).lockDrawer();
                getActivity().onBackPressed();
            }
        }
    }
}
