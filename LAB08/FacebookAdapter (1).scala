import com.restfb._
import com.restfb.types._
import java.io.FileWriter
import java.util.Calendar
import scala.concurrent.Future
import java.lang.Thread
import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import scala.collection.Seq

object FacebookAdapter {

  val myAppSecret = "0077de9e55ef44729ada66f6af5d0ccf" //any String (or Note1)
  val currentAccessToken = "EAA5MbP25ijsBABe4fBL5h9wcPw42ANMMq6ZBXu4vjqTbkuZAf3gjVysZBXGl7NheiB9DY5r0oZC8nhrvRia0kmhK1c7WQVSbyTLoqJjYnCQsT03VssmtMQ9OL9lInKtdjo3vIHGk1ggP4PgZCW1xBKZBZA67BPf9mQAZBZBggFw9A5wZDZD"
  //Acces Token should be still viablle (till february 2021)
  val parameters = "name,id,likes.summary(true)" // parameters to get name, id, likes with a total count of likes

  class MyFacebookClient(currentAccessToken: String) extends DefaultFacebookClient(currentAccessToken, myAppSecret,
    Version.VERSION_5_0) {
  }


  def getUser(accessToken: String, id: String) = Future{
    val client = new MyFacebookClient(accessToken)
    val user = client.fetchObject(id, classOf[User])
    user
  }

// Getting user with no parameters passed returns only id and name
  // so i also defined getUser_with_parameters to be able to extract likes etc

  def getUser_with_parameters(accessToken: String, id: String, parameters : String, fields:String = "fields") = Future{
    val client = new MyFacebookClient(accessToken)
    val user = client.fetchObject(id, classOf[User], Parameter.`with`(fields, parameters))
    user
  }

  def write_to_file(logFile:String, text:String) = Future{
    val write = new FileWriter(logFile, true)
    write.write(text + "\n ")
    write.close()

  }

  def print_likes(user1:String, user2:String) = Future {
    val u1 = getUser_with_parameters(currentAccessToken, user1, parameters)
    val u2 = getUser_with_parameters(currentAccessToken, user1, parameters)

    val futures = for {
     r1 <- u1
     r2 <- u2
      } yield println(r1.getName + ", likes:" + r1.getLikes.getTotalCount + " vs. " + r2.getName + ", likes:" + r2.getLikes.getTotalCount )

  }


  def comparingLikes(logFile:String, user1:String, user2:String) = {


    val future1 = getUser(currentAccessToken, user1)
    val future2 = getUser(currentAccessToken, user1)
    val future3 = write_to_file(logFile, Calendar.getInstance().getTime() + " " + user1 + " " + user2)
    val future4 = print_likes(user1,user2)

    val futures = Future.sequence(Seq(future1,future2,future3,future4))
    futures onComplete {
      case Success(value) =>
      case Failure(exception) => println("Something went wrong")
    }
  Thread.sleep(10000)
    //Q: I think using Awaits.result(futures, duration) here doesnt change a alot since its our last operation so we won't block anything that happens after our part of the code
    // Am i right? I still went with onComplete since its a better practice to learn.

  }


def main(args: Array[String]): Unit = {
    val test_path = "C:\\Users\\vb\\IdeaProjects\\lab8\\src\\main\\scala\\test.txt"
    val my_id = "1323655868002597" //
    comparingLikes(test_path, my_id, my_id)


  }


}

