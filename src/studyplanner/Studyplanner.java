package studyplanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 *
 * @author M Ratna Sri
 */
class Task {

    String subject;
    String description;
    LocalDate deadline;
    int priority;
}

public class Studyplanner extends Applet implements ActionListener {

    Button subjects, tasks, schedule, addSubject, add_task, done;
    TextField t1, t2, t3, ts;
    Choice c = new Choice();
    List list_subjects = new List();
    List list_tasks = new List();
    Task taskArr[] = new Task[100];
    Panel pw;
    Panel pc;
    Panel pt = new Panel();
    Panel pat = new Panel();
    Panel ps = new Panel();
    Panel pas = new Panel();
    Label l1 = new Label("Subject");
    Label l2 = new Label("Task");
    Label l3 = new Label("Priority(0-10)");
    Label l4 = new Label("Deadline(yyyy-mm-dd)");
    Label ls = new Label("Subject Name: ");
    Label lt = new Label("Study Planner");
    int n = 0;

    @Override
    public void init() {
        System.out.println("Started");
        subjects = new Button("Subjects");
        tasks = new Button("Tasks");
        schedule = new Button("Schedule");
        addSubject = new Button("+ Add Subject");
        add_task = new Button("+ Add Task");
        done = new Button("Add Subject");
        t1 = new TextField(10);
        t2 = new TextField(10);
        t3 = new TextField(10);
        ts = new TextField(10);

        //Panel p = new Panel();
        this.setLayout(new BorderLayout());
        lt.setAlignment(Label.CENTER);
        lt.setForeground(Color.blue);
        lt.setBackground(Color.green);
        add(lt,BorderLayout.NORTH);
        pw = new Panel();
        pc = new Panel();
        pw.setLayout(new GridLayout(3, 1));
        add(pw, BorderLayout.WEST);
        pc.setLayout(new BorderLayout());

        pw.add(subjects);
        pw.add(tasks);
        pw.add(schedule);
        subjects.addActionListener(this);
        tasks.addActionListener(this);
        schedule.addActionListener(this);
        addSubject.addActionListener(this);
        add_task.addActionListener(this);
        done.addActionListener(this);
        list_subjects.add("Other");
        /* list_subjects.add("Ada");
         list_subjects.add("dbms");
         list_subjects.add("compiler");
         list_subjects.add("Software Engineering");
         list_subjects.add("Java Programming");*/

        this.add(pc, BorderLayout.CENTER);
        pc.add(addSubject, BorderLayout.NORTH);
        pc.add(list_subjects);

    }

    void schedule() {
        int i, j, pos;
        for (i = 1; i < n; i++) {
            Task min = taskArr[i];
            pos = i;
            for (j = i + 1; j <= n; j++) {
                if (taskArr[j].deadline.isBefore(min.deadline)) //.before(min.deadline))
                {
                    min = taskArr[j];
                    pos = j;
                } else if (taskArr[j].deadline.equals(min.deadline)) {
                    if (taskArr[j].priority > min.priority) {
                        min = taskArr[j];
                        pos = j;
                    }
                }
            }
            taskArr[pos] = taskArr[i];
            taskArr[i] = min;
        }
    }

    public void listTasks() {
        list_tasks.removeAll();
        for (int i = 1; i <= n; i++) {
            list_tasks.add(taskArr[i].description + "(" + taskArr[i].subject + ")");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Subjects")) {
            pat.setVisible(false);
            pt.setVisible(false);
            ps.setVisible(false);
            pas.setVisible(false);
            pc.setVisible(true);
            //addSubject.setLabel("+ Add Subject");
            //pc.add(addSubject,BorderLayout.NORTH);
            //pc.add(list_subjects);
        } else if (ae.getActionCommand().equals("Tasks")) {
            pat.setVisible(false);
            pc.setVisible(false);
            ps.setVisible(false);
            pas.setVisible(false);
            pt.setLayout(new BorderLayout());
            this.add(pt, BorderLayout.CENTER);
            pt.add(add_task, BorderLayout.NORTH);
            pt.add(list_tasks);
            pt.setVisible(true);
            //addSubject.setVisible(false);
            //add_task.setVisible(true);
            //pc.repaint();
            //listTasks();
            //pc.add(list_tasks);
        } else if (ae.getActionCommand().equals("Schedule")) {
            pat.setVisible(false);
            pc.setVisible(false);
            pt.setVisible(false);
            pas.setVisible(false);

            this.add(ps, BorderLayout.CENTER);
            schedule();
            listTasks();
            ps.add(list_tasks);
            ps.setVisible(true);
            System.out.println("Schedule made");
        } else if (ae.getActionCommand().equals("+ Add Task")) {
            System.out.println("in add task");
            pc.setVisible(false);
            pt.setVisible(false);
            ps.setVisible(false);
            pat.setVisible(true);
            pas.setVisible(false);
            this.add(pat, BorderLayout.CENTER);
            c.removeAll();
            for (int i = 0; i < list_subjects.getItemCount(); i++) {
                c.add(list_subjects.getItem(i));
            }
            pat.add(l1);
            pat.add(c);
            pat.add(l2);
            pat.add(t1);
            pat.add(l3);
            pat.add(t2);
            pat.add(l4);
            pat.add(t3);
            pat.add(done);
            done.setLabel("Add Task");
        } else if (ae.getActionCommand().equals("Add Task")) {
            n++;
            Task newTask = new Task();
            newTask.subject = c.getSelectedItem();
            newTask.description = t1.getText();
            newTask.priority = Integer.parseInt(t2.getText());
            newTask.deadline = LocalDate.parse(t3.getText());
            taskArr[n] = newTask;

            pat.setVisible(false);
            pc.setVisible(false);
            ps.setVisible(false);
            pas.setVisible(false);

            pt.setLayout(new BorderLayout());
            this.add(pt, BorderLayout.CENTER);
            pt.add(add_task, BorderLayout.NORTH);
            listTasks();
            pt.add(list_tasks);
            pt.setVisible(true);
            System.out.println("Task added");
        } else if (ae.getActionCommand().equals("+ Add Subject")) {
            System.out.println("in add subject");
            pc.setVisible(false);
            pt.setVisible(false);
            ps.setVisible(false);
            pat.setVisible(false);
            this.add(pas, BorderLayout.CENTER);
            pas.add(ls);
            pas.add(ts);
            pas.add(done);
            done.setLabel("Add Subject");
            pas.setVisible(true);
            this.repaint();
        } else if (ae.getActionCommand().equals("Add Subject")) {
            System.out.println("in add subject");
            list_subjects.add(ts.getText());
            pas.setVisible(false);
            pt.setVisible(false);
            ps.setVisible(false);
            pat.setVisible(false);
            pc.setVisible(true);
        }
    }
}
/*<APPLET code=Studyplanner width=500 height=500>
 </APPLET>
 */
