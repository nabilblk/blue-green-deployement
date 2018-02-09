package ma.octo.utils

object ScriptingUtils {

  val randomData = new org.apache.commons.math.random.RandomDataImpl

  val selectRandomElementInList = (list: Option[Seq[String]]) => {
    val realList: Seq[String] = list.get
    val randomIndex = randomData.nextInt(0, realList.size - 1)
    val selected: Option[String] = Option(realList(randomIndex))
    //val selected: Option[String] = Option(realList(1))
    selected
  }

  val tomorrowDate = () => {
    val calendar = java.util.Calendar.getInstance();
    calendar.add(java.util.Calendar.DATE, 1);
    val daysDate = calendar.getTime();

    val adriaFormat = new java.text.SimpleDateFormat("dd-MM-yyyy")
    val daysDateFormatted = adriaFormat.format(daysDate)
    daysDateFormatted
  }

  val currentTimrestamp = () => {
    val timestamp = System.currentTimeMillis();
    timestamp
  }
}
