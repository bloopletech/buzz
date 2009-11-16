<?
include("buzz.php");
?>
<html>
   <head>
      <title>buzz</title>

      <link rel="stylesheet" type="text/css" href="style.css" />
   </head>
   <body>
      <div id="wrap">
         <div id="header"><img src="logo.png" alt="buzz" /></div>

         <div id="body">

            <div id="main">
               <h2>Picture this:</h2>
               <p>You are driving at 60 km/h when a six-year old child runs out on to the road 30 metres in front of you.
               Could you stop in time? buzz can tell you.<!--The car once you begin braking, will take 20 metres to stop. The car is travelling 16 meters a second.
               How long will it take for you to react to the child on the road and hit the brakes?--></p>

               <h2>What is buzz?</h2>
               <p>buzz is a reaction tester game; it tests you on your ability to react to changes on the screen.
               The quicker you respond to these changes, the higher your score.</p>
               <h2>How to play?</h2>
               <p>
                 <ul>
                    <li>Click Login to create a username or to login using an existing username.</li>
                    <li>Click the Start button in the game. The button will change to "Wait...".</li>
                    <li>When the button changes to "Click Me!", click the button once as quickly as possible.</li>
                 </ul>
               You will be scored based on how long it takes you to react to "Click Me!" i.e., how quickly you click the button.</p>
               <h2>Play online</h2>
               <p>
               <applet code="com.buzz.BuzzApplet" archive="buzz-applet.jar" width="570" height="23">
                  <div class="error">
                     Sorry, but you don't have Java, or Java isn't enabled in your browser. You can download Java at <a href="http://www.java.com">java.com</a>.
                  </div>
               </applet>
               </p>
               <h2>Download</h2>
               <p>You can download buzz to play it without opening your web browser. Click <a href="buzz.jar">here</a> to download.</p>
               <h2>High scores</h2>
               <p>
               <table class="scoretable">
               <thead class="scoretablehead">
               <tr>
                  <td>Username</td>
                  <td>React. Time</td>
                  <td>Score</td>
                  <td>Date/Time of Game</td>
               </tr>
               </thead>
               <tbody>
               <?
                  $scores = getHighScores(50);
                  foreach($scores as $c => $i)
                  {
                  ?>
                  <tr>
                     <td><a class="scoretablea" href="./?details<?=$i[1];?>" <? if($c == 0) { ?>id="byname"<? }; ?>><?=$i[1];?></a></td>
                     <td class="td2"><?=number_format($i[2], 0, ".", ",");?> ms</td>
                     <td class="td2"<? if($c == 0) { ?> id="topscore"<? }; ?>><?=number_format(round(pow(1.01, 1000 - $i[2])), 0, ".", ",");?></td>
                     <td><?$temp = strtotime($i[3]); echo strftime("%d/%m/%Y - %I:%M %p", $temp);?></td>
                  </tr>
                  <?
                  }
                  if(count($scores) == 0)
                  {
                  ?>
                  <tr colspan="4">
                  <td><i>There's no scores.</i></td>
                  </tr>
                  <?
                  }
               ?>
               </tbody>
               </table>
               </p>
               <h2>How you're scored</h2>
               <p>First, the length of time between the button changing to "Click Me!", and you clicking is measured.
               Your score is: 1.01<sup>1s - length of time</sup>.</p>
               <p class="bottom">Copyright &copy; 2007 Brenton Fletcher.</p>
            </div>
         </div>
         <div class="footer">Check out my portfolio at <a href="http://i.budgetwebdesign.org">i.budgetwebdesign.org</a>. &copy; Brenton Fletcher. Comments? e-mail me: <a href="mailto:impactbc@hotmail.com">impactbc@hotmail.com</a>.</div>
      </div>
   </body>
</html>