package Red_Black;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Source extends Application implements Initializable {
	static Source x = new Source();

	@FXML
	TextField insertwordtxtfield = new TextField();
	@FXML
	javafx.scene.control.Button Add = new javafx.scene.control.Button();
	@FXML
	javafx.scene.control.Button Back = new javafx.scene.control.Button();
	@FXML
	javafx.scene.control.Button InsertFile = new javafx.scene.control.Button();
	@FXML
	javafx.scene.control.Button InsertWord = new javafx.scene.control.Button();
	@FXML
	javafx.scene.control.Button DeleteWord = new javafx.scene.control.Button();
	@FXML
	javafx.scene.control.Button DeleteFile = new javafx.scene.control.Button();
	@FXML
	javafx.scene.control.Button Search = new javafx.scene.control.Button();
	@FXML
	javafx.scene.control.Button Delete = new javafx.scene.control.Button();
	@FXML
	javafx.scene.control.Button Search2 = new javafx.scene.control.Button();
	@FXML
	Label DisSizeLabel = new Label();
	@FXML
	Label HeightLabel = new Label();
	MediaPlayer mediaPlayer1;
	@FXML
	public ListView<ColoredText> MylistView2 = new ListView<ColoredText>();
	public static ObservableList<String> myobservablelist = FXCollections.observableArrayList();
	private Stage PrimaryStage;
	public static RB_Tree rb_Tree = new RB_Tree();

	public void Preview(String Name, Color C) {
		MylistView2.getItems().add(new ColoredText(Name, C));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.PrimaryStage = primaryStage;
		showPrimaryPage();
	}

	public void showPrimaryPage() throws IOException {
		playmusic();
		Parent mainViewRoot = FXMLLoader.load(getClass().getResource("GUI.fxml"));
		Scene scene = new Scene(mainViewRoot);
		PrimaryStage.setScene(scene);
		PrimaryStage.setTitle("Dictionary Ele5wa");
		PrimaryStage.getIcons().add(new Image("icon.jpg"));
		PrimaryStage.setMaximized(true);
		PrimaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void inorder(Node root) {

		if (root != Node.nil) {
			inorder(root.left);
			if (root.color == 1) {
				Preview(root.data, Color.RED);
			} else {

				Preview(root.data, Color.BLACK);
			}
			inorder(root.right);
		}
	}

	// ____________________________GUI______________________________
	@FXML
	public void Back() {
		insertwordtxtfield.setText("");
		InsertWord.setVisible(true);
		InsertFile.setVisible(true);
		DeleteFile.setVisible(true);
		DeleteWord.setVisible(true);
		Search.setVisible(true);
		insertwordtxtfield.setVisible(false);
		Back.setVisible(false);
		Add.setVisible(false);
		Delete.setVisible(false);
		Search2.setVisible(false);

	}

	@FXML
	public void ShowAddWord() {
		InsertWord.setVisible(false);
		InsertFile.setVisible(false);
		DeleteFile.setVisible(false);
		DeleteWord.setVisible(false);
		Search.setVisible(false);
		insertwordtxtfield.setVisible(true);
		insertwordtxtfield.requestFocus();
		Back.setVisible(true);
		Add.setVisible(true);

	}

	@FXML
	public void AddWord() {
		String Pass = insertwordtxtfield.getText();
		if (rb_Tree.findNode(new Node(Pass), rb_Tree.root) == null) {
			rb_Tree.Insert(Pass);
			HeightLabel.setText(rb_Tree.UpdateTreeHeight(rb_Tree.root) + "");
			rb_Tree.numberofwords++;
			DisSizeLabel.setText(rb_Tree.numberofwords + "");
			MylistView2.getItems().clear();
			inorder(rb_Tree.root);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Word Already Exists!!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}

	}

	@FXML
	public void ShowDeleteWord() {
		InsertWord.setVisible(false);
		InsertFile.setVisible(false);
		DeleteFile.setVisible(false);
		DeleteWord.setVisible(false);
		Search.setVisible(false);
		insertwordtxtfield.setVisible(true);
		insertwordtxtfield.requestFocus();
		Back.setVisible(true);
		Delete.setVisible(true);

	}

	@FXML
	public void SearchWord() {
		if (rb_Tree.findNode(new Node(insertwordtxtfield.getText()), rb_Tree.root) != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Word Found!!");
			alert.setHeaderText(null);
			alert.showAndWait();
		} else {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Word Doesn't Exist!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	}

	@FXML
	public void ShowSearchWord() {
		InsertWord.setVisible(false);
		InsertFile.setVisible(false);
		DeleteFile.setVisible(false);
		DeleteWord.setVisible(false);
		Search.setVisible(false);
		insertwordtxtfield.setVisible(true);
		insertwordtxtfield.requestFocus();
		Back.setVisible(true);
		Search2.setVisible(true);

	}

	@FXML
	public void DeleteWord() {
		if (rb_Tree.delete(new Node(insertwordtxtfield.getText())) == true) {
			rb_Tree.numberofwords--;
			HeightLabel.setText(rb_Tree.UpdateTreeHeight(rb_Tree.root) + "");
			DisSizeLabel.setText(rb_Tree.numberofwords + "");
			MylistView2.getItems().clear();
			inorder(rb_Tree.root);
		}

		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Word Doesn't Exist!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}

	}

	@FXML
	public void AddFile() throws IOException {

		FileChooser x = new FileChooser();
		FileChooser.ExtensionFilter xi = new ExtensionFilter("TXT files (*.txt)", "*.txt");
		x.getExtensionFilters().add(xi);
		File file = x.showOpenDialog(PrimaryStage);
		if (file == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("No File Selected");
			alert.setHeaderText(null);
			alert.showAndWait();
		} else {
			FileReader ReadDic = new FileReader(file);
			BufferedReader R = new BufferedReader(ReadDic);
			rb_Tree.deleteTree();
			MylistView2.getItems().clear();
			while (true) {
				String df = R.readLine();
				if (df == null) {
					break;
				}
				rb_Tree.Insert(df);
				rb_Tree.numberofwords++;
			}
			R.close();
			HeightLabel.setText(rb_Tree.UpdateTreeHeight(rb_Tree.root) + "");
			DisSizeLabel.setText(rb_Tree.numberofwords + "");
			inorder(rb_Tree.root);
		}
	}

	public void playmusic() {
		String musicFile = "F:\\projects eclipse\\Red_Black_2\\src\\music.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer1 = new MediaPlayer(sound);
		mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer1.play();

	}

	@FXML
	public void Deletefile() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to delete current file?");
		alert.setHeaderText(null);
		alert.showAndWait();
		if (alert.getResult().getText().equalsIgnoreCase("ok") == true) {
			rb_Tree.deleteTree();
			HeightLabel.setText(rb_Tree.UpdateTreeHeight(rb_Tree.root) + "");
			MylistView2.getItems().clear();
			DisSizeLabel.setText(rb_Tree.numberofwords + "");
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MylistView2.setCellFactory(lv -> new ListCell<ColoredText>() {
			@Override
			protected void updateItem(ColoredText item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setText(null);
					setTextFill(null);
				} else {
					setText(item.getText());
					setTextFill(item.getColor());
				}
			}
		});
		// inorder(rb_Tree.root);
	}

}
