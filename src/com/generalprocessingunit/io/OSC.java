package com.generalprocessingunit.io;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

import java.util.Arrays;
import java.util.List;

public class OSC {
    private static OSCPortOut oscPortOut;

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

    public static void sendMsg(String address, Object message) {
        sendMsg(address, Arrays.asList((Object) message));
    }

    public static void sendMsg(String address, List<Object> messages) {
        OSCMessage msg = new OSCMessage(address, messages);
        try {
            getOscPortOut().send(msg);
        } catch (Exception e) {
            System.out.println("Couldn't send OSC");
        }
    }
}
