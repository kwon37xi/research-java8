// jjs -fx lecture13.js 로 실행한다.
var message = new javafx.scene.control.Label('Hello, JavaFX!');
message.font = new javafx.scene.text.Font(100);
$STAGE.scene = new javafx.scene.Scene(message);
$STAGE.title = 'Hello';
