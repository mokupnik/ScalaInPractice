package models

case class Student(index: Int, name: String, year: Short)
case class Lecture(id: String, name: String)
//e.g. Lecture("SIP", "Scala in Practice")
case class Enrollment(id: String, students: List[Student])

object DB {
  val student = Student(1, "Maciej Okupnik", 4)
  val student_2 = Student(2, "Jan Nowak", 1)
  val student_3 = Student(3, "Maks Debesciak", 2)
  val student_4 = Student(4, "Tom Waits", 6)
  val student_5 = Student(5, "Phoebe Bridgers", 3)
  val student_6 = Student(6, "Elon Musk", 2)
  val student_7 = Student(7, "Henry Rollins", 6)
  val student_8 = Student(8, "Ian MacKaye", 2)
  val student_9 = Student(9, "John Coltrane", 3)
  val student_10 = Student(10, "Conor Oberst", 1)
  val all_students = List(student, student_2, student_3, student_4, student_5, student_6,
    student_7, student_8, student_9, student_10)

  val MIA = Lecture("MIA", "Metody implementacji algorytmow")
  val ML = Lecture("ML", "Machine Learning")
  val EA = Lecture("EWO", "Algorytmy ewolucyjne")
  val SIP = Lecture("SIP", "Scala in Practice")
  val ALL_Lectures = List(MIA, ML, EA, SIP)


  val students_SIP = List(student, student_2, student_4)
  val students_MIA = List(student_6, student_5, student_10, student_2, student_3)
  val students_ML = List(student_3, student_4, student, student_9, student_8)
  val students_EA = List(student, student_7, student_6, student_3)


  val SIP_enrollment = Enrollment("SIP", students_SIP)
  val MIA_enrollment = Enrollment("MIA", students_MIA)
  val ML_enrollment = Enrollment("ML", students_ML)
  val EA_enrollment = Enrollment("EWO", students_EA)
  val ALL_enrollments = List(SIP_enrollment, MIA_enrollment, ML_enrollment, EA_enrollment)




}