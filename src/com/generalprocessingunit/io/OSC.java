package com.generalprocessingunit.io;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortIn;
import com.illposed.osc.OSCPortOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OSC {
    private static OSCPortOut oscPortOut;
    private static OSCPortIn oscPortIn;

    protected OSC() { /*Singleton*/ }

    private static OSCPortOut getOscPortOut() {
        if(null == oscPortOut) {
            try {
                oscPortOut = new OSCPortOut();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return oscPortOut;
    }

    private static OSCPortIn getOscPortIn() {
        if(null == oscPortIn) {
            try {
                oscPortIn = new OSCPortIn(7400);
                oscPortIn.startListening();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return oscPortIn;
    }

    public static void sendMsg(String address, Object message) {
        sendMsg(address, Arrays.asList((Object) message));
    }

    public static void sendMsg(String address, Object ... messages) {
        List<Object> msgList = new ArrayList<>(messages.length);
        for(Object message : messages) {
            msgList.add(message);
        }
        sendMsg(address, msgList);
    }

    public static void sendMsg(String address, List<Object> messages) {
        OSCMessage msg = new OSCMessage(address, messages);
        try {
            getOscPortOut().send(msg);
        } catch (Exception e) {
            System.out.println("Couldn't send OSC");
        }
    }

    public static void addListener(String address, OSCListener listener) {
        getOscPortIn().addListener(address, listener);
    }
}
