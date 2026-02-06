interface ModerationRule {
    int apply(int score);
}

class AttendanceModeration implements ModerationRule {
    public int apply(int score) {
        return score + 2;
    }
}

abstract class Evaluation {

    ModerationRule modRule;

    Evaluation(ModerationRule modRule) {
        this.modRule = modRule;
    }

    final void evaluate() {
        int theoryMarks = 65;
        int labMarks = 35;

        int finalScore = calculate(theoryMarks, labMarks);
        finalScore = modRule.apply(finalScore);

        grade(finalScore);
    }

    abstract int calculate(int theoryMarks, int labMarks);

    abstract void grade(int finalScore);
}

class BTech extends Evaluation {

    BTech(ModerationRule modRule) {
        super(modRule);
    }

    int calculate(int theoryMarks, int labMarks) {
        return theoryMarks + labMarks;
    }

    void grade(int finalScore) {
        if (finalScore >= 55) {
            System.out.println("BTech Result: PASS");
        } else {
            System.out.println("BTech Result: FAIL");
        }
    }
}

class MCA extends Evaluation {

    MCA(ModerationRule modRule) {
        super(modRule);
    }

    int calculate(int theoryMarks, int labMarks) {
        return (theoryMarks * 2 + labMarks) / 3;
    }

    void grade(int finalScore) {
        if (finalScore >= 60) {
            System.out.println("MCA Result: PASS");
        } else {
            System.out.println("MCA Result: FAIL");
        }
    }
}

public class EvaluationSystem {
    public static void main(String[] args) {

        Evaluation student1 = new BTech(new AttendanceModeration());
        student1.evaluate();

        Evaluation student2 = new MCA(new AttendanceModeration());
        student2.evaluate();
    }
}
