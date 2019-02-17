package tnt_codefest.diaeta.DietPlan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class DietPlanDialog extends AppCompatDialogFragment {

    private TaskListener listener;
    final CharSequence[] day1 = {" 2 Eggs"," 1 Hotdog"," 1 Cup of Rice "," 8 Glasses of Water "};
    final ArrayList selectedItems =new ArrayList();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("DAY #")
                .setMultiChoiceItems(day1, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int index, boolean isChecked) {
                        if (isChecked){
                            selectedItems.add(day1[index]);
                        }
                        else{
                            selectedItems.remove(day1[index]);
                        }
                    }
                })
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.completeTasks(selectedItems);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (TaskListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement TaskListener");
        }
    }

    public interface TaskListener{
        void completeTasks(ArrayList list_task);
    }
}
