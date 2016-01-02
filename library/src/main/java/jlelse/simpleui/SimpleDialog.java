/*
 *    Copyright 2015 Jan-Lukas Else
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package jlelse.simpleui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class SimpleDialog {

    Context context;

    public SimpleDialog(Context context) {
        this.context = context;
    }

    public void SimpleAlertDialog(String title, String message) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .show();
    }

    public void SimpleOKDialog(String title, String message) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .positiveText(android.R.string.ok)
                .show();
    }

    public void SimpleYesNoDialog(String title, String message, boolean cancelable, final DialogInterface.OnClickListener positiveOnClickListener, final DialogInterface.OnClickListener negativeOnClickListener, DialogInterface.OnCancelListener cancelListener) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .positiveText(android.R.string.yes)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        positiveOnClickListener.onClick(dialog, which.ordinal());
                    }
                })
                .negativeText(android.R.string.no)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        negativeOnClickListener.onClick(dialog, which.ordinal());
                    }
                })
                .cancelable(cancelable)
                .cancelListener(cancelListener)
                .show();
    }
}
