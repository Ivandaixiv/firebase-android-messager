package com.treehouse.realtimemessaging.messages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.treehouse.realtimemessaging.R;

/**
 * Responsible for displaying a list of messages from Firebase, listening for new messages, and
 * sending messages to Firebase.
 * <p>
 * The key functionality we implement from Firebase in this particular Activity is the
 * ChildEventListener. That interface has five methods that we need to override, including
 * onChildAdded, onChildChanged, onChildRemoved, onChildMoved, and onCancelled. In this workshop,
 * we are only going to override onChildAdded to add messages to our RecyclerView as they are
 * added in Firebase.
 */
public class MessagingActivity extends AppCompatActivity {

//    TODO Add a constant field for the name of your messages node from Firebase

//    TODO Get a reference to your FirebaseDatabase

//    TODO Get a reference to the "messages" node of your Firebase

    private RecyclerView messagesList;
    private MessageAdapter messageAdapter;

    private EditText messageEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        messagesList = (RecyclerView) findViewById(R.id.messages_list);
        messagesList.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false));

        messageEntry = (EditText) findViewById(R.id.message_entry);
        messageEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean enterWasPressed = event == null
                                || (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN);
                if (enterWasPressed) {
                    sendMessage(null);
                    return true;
                }

                return false;
            }
        });

        messageAdapter = new MessageAdapter();
        messagesList.setAdapter(messageAdapter);
    }

    /**
     * Called when the "Send Message" button is clicked.
     *
     * @param view The button that was clicked.
     */
    public void sendMessage(View view) {
//        TODO Call the method that pushes the message to Firebase when the send button is clicked

//        TODO Delete the Toast message
        Toast.makeText(
                this,
                "TODO: Send this message to Firebase",
                Toast.LENGTH_LONG)
                .show();

        resetMessageEntry();

        hideKeyboard();
    }

//    TODO Write a method that pushes a message to Firebase

    /**
     * Hides the soft keyboard. Used after a message is sent.
     */
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(messageEntry.getWindowToken(), 0);
    }

    /**
     * Clears the entered text. Used after a message is sent.
     */
    private void resetMessageEntry() {
        messageEntry.setText("");
    }

    /**
     * To give a nice effect when entering into this Activity, we can use this method to scroll
     * to the bottom of the list of messages. It is also used when a new message is sent.
     */
    private void scrollToMostRecentMessage() {
        int mostRecentMessageIndex = messageAdapter.getItemCount() - 1;
        messagesList.smoothScrollToPosition(mostRecentMessageIndex);
    }

    /**
     * Handy mehtod for starting this Activity.
     * @param context Used to start Activity
     */
    public static void start(Context context) {
        Intent startMessagingActivity = new Intent(context, MessagingActivity.class);
        context.startActivity(startMessagingActivity);
    }
}