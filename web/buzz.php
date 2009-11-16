<?
include("config.php");
mysql_pconnect($CONFIG['host'], $CONFIG['username'], $CONFIG['password']);
mysql_select_db($CONFIG['database']);

//$query = "INSERT INTO stats VALUES(NOW(), \"".$_SERVER["REMOTE_ADDR"]."\", \"".mysql_real_escape_string(substr($_SERVER["REQUEST_URI"], 0, 100))."\", \"".mysql_real_escape_string(substr($_SERVER["HTTP_USER_AGENT"], 0, 100))."\")";
//mysql_query($query);

function getHighScores($limit = 10)
{
   $query = "SELECT * FROM highscores ORDER BY score ASC LIMIT ".$limit;
   $result = mysql_query($query);

   $output = array();
   $row = mysql_fetch_row($result);
   while($row != null)
   {
      $output[] = $row;
      $row = mysql_fetch_row($result);
   }

   return $output;
}

function getUserScores($uname)
{
   $query = "SELECT * FROM highscores WHERE username=\"$uname\" ORDER BY datestamp DESC";
   $result = mysql_query($query);

   $output = array();
   $row = mysql_fetch_row($result);
   while($row != null)
   {
      $output[] = $row;
      $row = mysql_fetch_row($result);
   }

   return $output;
}

function addScore($uname, $score)
{
   $query = "INSERT INTO highscores VALUES(DEFAULT, \"$uname\", $score, NOW())";
   mysql_query($query);
}

function buzzHandler($message)
{
   echo "901";
   die();
}
?>
