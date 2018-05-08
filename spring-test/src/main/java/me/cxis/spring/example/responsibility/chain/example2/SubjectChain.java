package me.cxis.spring.example.responsibility.chain.example2;

import java.util.List;

public class SubjectChain {

    private List<Subject> successors;

    private int index = 0;

    public SubjectChain(List<Subject> successors) {
        this.successors = successors;
    }

    public void process() {
        if (index < successors.size()) {
            successors.get(index++).execute(this);
        }
    }

}
