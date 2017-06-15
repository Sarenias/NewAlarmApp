using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class NewBehaviourScript : MonoBehaviour {
    AndroidJavaClass jc;
    AndroidJavaObject jo;
    AndroidJavaClass unityReceive;
    AndroidJavaObject receiver;
     void Start()
    {
        AndroidJavaClass jclass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject activity = jclass.GetStatic<AndroidJavaObject>("currentActivity");
        unityReceive = new AndroidJavaClass("android.unity.alarmlibrary.UnityReceiver");
        unityReceive.CallStatic("createInstance");
        receiver = unityReceive.GetStatic<AndroidJavaObject>("instance");
        jo = new AndroidJavaObject("android.unity.alarmlibrary.StartMain", activity);
        print("Start has been called");
    }

    void Update()
    {
        bool finished = unityReceive.CallStatic<bool>("getStatus");
        print("Update is being called");
        if (finished == true)
        {
            print("Is finished");
            SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex + 1);
        }
    }

}

