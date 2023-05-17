package me.cxis.spring.example.responsibility.chain.example1;

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
        Subject subjectA = new SubjectA();
        Subject subjectB = new SubjectB();
        Subject subjectC = new SubjectC();
        Subject subjectD = new SubjectD();

        subjectA.setSuccessor(subjectB);
        subjectB.setSuccessor(subjectC);
        subjectC.setSuccessor(subjectD);

        subjectA.execute();
    }
}
