package org.vso.livedata;

import java.util.ArrayList;
import java.util.List;

public class LiveData<T> {

    private T data;
    private List<LiveDataListener<T>> listeners;

    public LiveData(T data) {
        this.data = data;
        listeners = new ArrayList<>();
    }

    public void post(T data) {
        this.data = data;
        notifyAllListeners();
    }

    private void notifyAllListeners() {
        listeners.forEach(liveDataListener -> liveDataListener.onNewData(data));
    }

    public void subscribe(LiveDataListener<T> listener) {
        listeners.add(listener);
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return (String) data;
    }
    public interface LiveDataListener<T> {
        void onNewData(T data);
    }
}
