//Meetkumar Saspara
//115971301
//R:01

/**
 * Represents a line of students in a lunch queue.
 */

public class StudentLine {

  /**
   * An array to store the students.
   */
  public final int CAPACITY = 20;
  private Student[] students;
  public int studentCount;

  /**
   * Constructs an empty StudentLine object with a capacity of 20 students.
   */

  StudentLine() {
    this.students = new Student[CAPACITY];
    studentCount = 0;
  }

  /**
   * Gets the number of students in the line.
   *
   * @return the number of students in the line
   */

  public int numStudents() {
    return this.studentCount;
  }

  /**
   * Gets the student at the specified index in the line.
   *
   * @param index the index of the student to retrieve
   * @return the student at the specified index
   * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
   */

  public Student getStudent(int index) throws ArrayIndexOutOfBoundsException {
    if (this.students == null || this.students.length == 0) {
      throw new ArrayIndexOutOfBoundsException(
        "There are no students in the line."
      );
    }

    if (
      (
        this.students[index] == null || this.students[index].getName() == null
      ) ||
      index > numStudents()
    ) {
      throw new ArrayIndexOutOfBoundsException(
        "That's an invalid index. The line was not updated"
      );
    }

    return students[index];
  }

  /**
   * Removes the student at the specified index from the line.
   *
   * @param index the index of the student to remove
   * @return the removed student
   * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
   * @throws EmptyLineException             if attempting to remove from an empty line
   */

  public Student removeStudent(int index)
    throws ArrayIndexOutOfBoundsException {
    if (index < 0 || index >= numStudents()) {
      throw new ArrayIndexOutOfBoundsException(
        "That's an invalid index. The line was not updated"
      );
    }
    Student tempStudent = this.students[index];
    for (int i = index; i < students.length - 1; i++) {
      students[i] = students[i + 1];
    }
    students[students.length - 1] = null;
    this.studentCount -= 1;
    return tempStudent;
  }

  /**
   * Bullies the student at the specified index from the line.
   *
   * @param index the index of the student to bully
   * @return the bullied student
   * @throws EmptyLineException if attempting to bully from an empty line
   */

  public Student bullyStudent(int index) throws EmptyLineException {
    if (this.studentCount == 0) {
      throw new EmptyLineException(
        "There was no one in line for the bully to bully"
      );
    }
    Student tempStudent = this.students[index];
    for (int i = index; i < students.length - 1; i++) {
      students[i] = students[i + 1];
    }
    students[students.length - 1] = null;
    this.studentCount -= 1;
    return tempStudent;
  }

  /**
   * Serves lunch to the student at the specified index from the line.
   *
   * @param index the index of the student to serve lunch
   * @return the served student
   * @throws EmptyLineException if attempting to serve lunch from an empty line
   */

  public Student ServeStudent(int index) throws EmptyLineException {
    if (this.studentCount == 0) {
      throw new EmptyLineException(
        "There are no students to serve lunch to. Mystery Meat Martha is sad"
      );
    }
    Student tempStudent = this.students[index];
    for (int i = index; i < students.length - 1; i++) {
      students[i] = students[i + 1];
    }
    students[students.length - 1] = null;
    this.studentCount -= 1;
    return tempStudent;
  }

  /**
   * Adds a student to the line at the specified index.
   *
   * @param index   the index at which to add the student
   * @param student the student to add
   * @throws DeanException                if the line is full
   * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
   */

  public void addStudent(int index, Student student)
    throws DeanException, InvalidArgumentException {
    if (numStudents() >= 19) {
      throw new DeanException(
        "You tried to add a student to a full lunch line. Dean Mean has picked up the student and given them a healthy dose of detention. Therefore, they will not be added to the lunch line"
      );
    }

    if (student.getMoney() < 0) {
      throw new InvalidArgumentException(
        "You can't have debt in middle school. The lunch line was not updated."
      );
    }

    for (int i = this.studentCount; i >= index; i--) {
      students[i + 1] = students[i];
    }
    this.students[index] = student;
    studentCount++;
  }

  /**
   * Cuts the student to the specified index from the line to his friend.
   *
   * @param index   the index of the student to cut
   * @param student the student to add
   * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
   */
  public void cutStudent(int index, Student student)
    throws ArrayIndexOutOfBoundsException {
    if (index < 0 || index >= numStudents()) {
      throw new ArrayIndexOutOfBoundsException(
        "That's an invalid index. The line was not updated"
      );
    }

    for (int i = this.studentCount; i >= index; i--) {
      students[i + 1] = students[i];
    }
    this.students[index] = student;
    studentCount++;
  }

  /**
   * Swaps the positions of two students in the line.
   *
   * @param index1 the index of the first student
   * @param index2 the index of the second student
   * @throws ArrayIndexOutOfBoundsException if either index is out of bounds
   */

  public void swapStudents(int index1, int index2)
    throws ArrayIndexOutOfBoundsException {
    if (index1 == index2) {
      throw new ArrayIndexOutOfBoundsException(
        "You cannot trade places with yourself. The lunch line was not updated"
      );
    }
    if (
      index1 < 0 ||
      index1 >= studentCount ||
      index2 < 0 ||
      index2 >= studentCount
    ) {
      throw new ArrayIndexOutOfBoundsException(
        "That's an invalid index. The line was not updated"
      );
    }

    Student temp = students[index1];
    students[index1] = students[index2];
    students[index2] = temp;
  }

  /**
   * Clones the student line object.
   *
   * @return a new StudentLine object with the same students as this line
   */

  public Object clone() {
    StudentLine cloneObj = new StudentLine();
    cloneObj.studentCount = studentCount;
    for (int i = 0; i <= studentCount; i++) {
      if (students[i] != null) {
        cloneObj.students[i] = students[i].clone();
      }
    }
    return cloneObj;
  }

  /**
   * Checks if this student line is equal to another object.
   *
   * @param o the object to compare with this student line
   * @return true if the object is a student line with the same students in the same order, otherwise false
   */

  public boolean equals(Object o) {
    if (!(o instanceof StudentLine)) {
      return false;
    }

    StudentLine s = (StudentLine) o;
    if (studentCount != s.studentCount) {
      return false;
    }
    for (int i = 0; i < studentCount; i++) {
      if (!(this.students[i].equals(s.students[i]))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns a string representation of the student line.
   *
   * @return a string representation of the student line
   */

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < studentCount; i++) {
      stringBuilder
        .append(i + 1)
        .append(".")
        .append(students[i].getName())
        .append(" ")
        .append("$")
        .append(students[i].getMoney())
        .append("\n");
    }
    return stringBuilder.toString();
  }
}
