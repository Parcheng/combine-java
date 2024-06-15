package com.parch.combine.tool.base.event;

import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ExecutorService;

public class EventObservable {

    private String subject;
    private boolean changed = false;
    private Vector<IEventObserver> obs;

    public EventObservable(String subject) {
        this.subject = subject;
        obs = new Vector<>();
    }

    public synchronized void addObserver(IEventObserver eventObserver) {
        if (eventObserver != null && !obs.contains(eventObserver)) {
            obs.addElement(eventObserver);
        }
    }

    public synchronized void deleteObserver(IEventObserver eventObserver) {
        obs.removeElement(eventObserver);
    }

    public void notify(Map<String, Object> msg) {
        Object[] arrLocal;
        synchronized (this) {
            if (!changed) {
                return;
            }
            arrLocal = obs.toArray();
            clearChanged();
        }

        String msgId = UUID.randomUUID().toString();
        for (int i = arrLocal.length-1; i>=0; i--) {
            IEventObserver observer = ((IEventObserver) arrLocal[i]);
            ExecutorService pool = observer.getPool();
            if (pool == null) {
                observer.execute(subject, msgId, msg);
            } else {
                pool.execute(() -> observer.execute(subject, msgId, msg));
            }
        }
    }

    public synchronized void clearObservers() {
        obs.removeAllElements();
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

    public synchronized int countObservers() {
        return obs.size();
    }
}
