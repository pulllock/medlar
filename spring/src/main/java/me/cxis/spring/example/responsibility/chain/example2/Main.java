package me.cxis.spring.example.responsibility.chain.example2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static class SubjectA extends Subject {
        @Override
        protected void handle() {
            System.out.println("SubjectA");
        }
    }

    private static class SubjectB extends Subject {
        @Override
        protected void handle() {
            System.out.println("SubjectB");
        }
    }

    private static class SubjectC extends Subject {
        @Override
        protected void handle() {
            System.out.println("SubjectC");
        }
    }

    private static class SubjectD extends Subject {
        @Override
        protected void handle() {
            System.out.println("SubjectD");
        }
    }

    public static void main(String[] args) {
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(new SubjectA());
        subjectList.add(new SubjectB());
        subjectList.add(new SubjectC());
        subjectList.add(new SubjectD());

        SubjectChain chain = new SubjectChain(subjectList);
        chain.process();
    }
}
