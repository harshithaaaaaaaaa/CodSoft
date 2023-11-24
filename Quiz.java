import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswerIndex;

    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class Quiz {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public Quiz() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
        score = 0;

        List<String> options1 = Arrays.asList("A. 4", "B. 6", "C. 2", "D. 8");
        questions.add(new Question("What is 2 + 2?", options1, 0));

        List<String> options2 = Arrays.asList("A. Germany", "B. Canada", "C. Paris", "D. India");
        questions.add(new Question("What is the capital of France?", options2, 2));

        List<String> options3 = Arrays.asList("A. 3", "B. 13", "C. 53", "D. 4");
        questions.add(new Question("What is 6/2?", options3, 0));

        List<String> options4 = Arrays.asList("A. 25", "B. 6", "C. 4", "D. 8");
        questions.add(new Question("What is 5*5?", options4, 0));

        List<String> options5 = Arrays.asList("A. 0", "B. 6", "C. 8", "D. 9");
        questions.add(new Question("What is 3+2-5?", options5, 0));

        startQuiz();
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        System.out.println("You have 10 seconds to answer each question.\n");

        askQuestion();
    }

    public void askQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            System.out.println("Question " + (currentQuestionIndex + 1) + ": " + question.getQuestionText());

            for (int i = 0; i < question.getOptions().size(); i++) {
                System.out.println(question.getOptions().get(i));
            }

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    timer.cancel();
                    currentQuestionIndex++;
                    askQuestion();
                }
            }, 10000);

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Select your answer (A, B, C, D): ");
                String userAnswer = scanner.next().toUpperCase();
                timer.cancel();

                if (userAnswer.equals("A") || userAnswer.equals("B") || userAnswer.equals("C") || userAnswer.equals("D")) {
                    int selectedOptionIndex = userAnswer.charAt(0) - 'A';

                    if (selectedOptionIndex == question.getCorrectAnswerIndex()) {
                        System.out.println("Correct!\n");
                        score++;
                    } else {
                        System.out.println("Incorrect. The correct answer was: " + question.getOptions().get(question.getCorrectAnswerIndex()) + "\n");
                    }

                    currentQuestionIndex++;
                    askQuestion();
                } else {
                    System.out.println("Invalid answer. Please select A, B, C, or D.");
                    askQuestion();
                }
            }
        } else {
            endQuiz();
        }
    }

    public void endQuiz() {
        System.out.println("Quiz completed! Your score: " + score + " out of " + questions.size());
        System.out.println("Correct: " + score + " Incorrect: " + (questions.size() - score));
    }

    public static void main(String[] args) {
        new Quiz();
    }
}
