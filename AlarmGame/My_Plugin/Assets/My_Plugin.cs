﻿using UnityEngine;
using UnityEngine.UI;
using System.Collections;
public class My_Plugin : MonoBehaviour
{
    void Start()
    {
        AndroidJavaClass pluginClass = new AndroidJavaClass("android.unity.myplugin.My_Plugin");
        GetComponent<Text>().text = pluginClass.CallStatic<string>("getMessage");
    }
}