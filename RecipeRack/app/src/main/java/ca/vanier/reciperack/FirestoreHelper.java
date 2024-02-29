package ca.vanier.reciperack;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirestoreHelper {

    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void fetchUserData(String uid){
        DocumentReference docRef = db.collection("users").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Log.d("DATA", "Document Snapshot data: " + document.getData());
                    } else {
                        Log.d("DATA", "No document exists");
                        insertNewUser(uid);
                    }
                } else {
                    Log.d("DATA", "Failed to get data ", task.getException());
                }
            }
        });
    }

    public static void insertNewUser(String uid){
        Recipe sample = new Recipe("Chocolate Cake", "Dessert", new String[]{"Butter", "Flour", "Cocoa Power", "Sugar"}, "Mix it all up and bake");
//        User user = new User(uid, new List<User>(uid, sample));
        Map<String, Object> userMap = new HashMap<>();
//        userMap.put("uid", user.getUid());
        userMap.put("recipes", Arrays.asList(sample));
        db.collection("users").document(uid).set(userMap)
                .addOnSuccessListener(aVoid -> {
                    Log.d("FIRESTORE", "Document Successfully Written");
                })
                .addOnFailureListener(e -> {
                    Log.d("FIRESTORE", "Error writing document ", e);
                });

    }


}
