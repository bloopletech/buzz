<?
include("buzz.php");

function buzzHandler1($message)
{
   echo "901";
   die();
}
set_error_handler("buzzHandler");

$mode = $_GET["mode"];
if($mode == "scores")
{
   addScore(addslashes($_GET["username"]), intval($_GET["score"]));
}
else if($mode == "info")
{
   function buzzHandler2($message)
   {
      echo "\n\n\n";
      die();
   }

   set_error_handler("buzzHandler2");

   $details = getUserScores(addslashes($_GET["username"]));
   if(count($details) > 0) echo round(pow(1.01, 1000 - $details[0][2]))."\n";
   else echo "0\n";

   $list = getHighScores(1);
   echo round(pow(1.01, 1000 - $list[0][2]))."\n";

   echo $list[0][1]."\n";

   echo "0.1";

   restore_error_handler();
   die();
}
else if($mode == "gets")
{
   $list = getHighScores(50);
   foreach($list as $items)
   {
      echo $items[1].",".$items[2].",".$items[3]."\\";
   }
}

restore_error_handler();
?>200