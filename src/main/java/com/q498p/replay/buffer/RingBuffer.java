package com.q498p.replay.buffer;

import java.util.*;

public class RingBuffer<T> {
    private final Object[] arr; private int idx=0; private boolean full=false;
    public RingBuffer(int size){ arr=new Object[size]; }
    public synchronized void add(T v){
        arr[idx++]=v; if(idx>=arr.length){ idx=0; full=true; }
    }
    @SuppressWarnings("unchecked")
    public synchronized List<T> getAll(){
        List<T> out=new ArrayList<>();
        if(!full){ for(int i=0;i<idx;i++) out.add((T)arr[i]); }
        else{ for(int i=idx;i<arr.length;i++) out.add((T)arr[i]); for(int i=0;i<idx;i++) out.add((T)arr[i]); }
        return out;
    }
}
