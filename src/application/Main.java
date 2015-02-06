package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import dwt.main.view.TextFieldTreeCellImpl;;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		BorderPane border = new BorderPane();
		        
		VBox vboxat = addVBoxAtTop();
		border.setTop(vboxat);
		//border.setLeft(addVBox());
		border.setLeft(addLeftPane());
		// Add a stack to the HBox in the top region
		//addStackPane(vbox);  
		        
		// To see only the grid in the center, uncomment the following statement
		// comment out the setCenter() call farther down        
//		        border.setCenter(addGridPane());
		        
		// Choose either a TilePane or FlowPane for right region and comment out the
		// one you aren't using        
		        //border.setRight(addFlowPane());
//		        border.setRight(addTilePane());
		        
		// To see only the grid in the center, comment out the following statement
		// If both setCenter() calls are executed, the anchor pane from the second
		// call replaces the grid from the first call        
		       // border.setCenter(addAnchorPane(addGridPane()));
		 
		 Scene scene = new Scene(border);
		 primaryStage.setScene(scene);
		 primaryStage.setTitle("Layout Sample");
		 
		 //window size max start
		 Screen screen = Screen.getPrimary();
		 Rectangle2D bounds = screen.getVisualBounds();

		 primaryStage.setX(bounds.getMinX());
		 primaryStage.setY(bounds.getMinY());
		 primaryStage.setWidth(bounds.getWidth());
		 primaryStage.setHeight(bounds.getHeight());
		 //window size max end
		 primaryStage.show();
	}
		 
		/*
		 * Creates an HBox with two buttons for the top region
		 */
		    
		private VBox addVBoxAtTop() {
			VBox vbox = new VBox();
		    MenuBar mainMenu = new MenuBar();
		    ToolBar toolBar = new ToolBar(); 
		    
		    //Create and add the "File" sub-menu options. 
		    Menu file = new Menu("File");
		    MenuItem openFile = new MenuItem("Open File");
		    MenuItem exitApp = new MenuItem("Exit");
		    file.getItems().addAll(openFile,exitApp);
		     
		    //Create and add the "Edit" sub-menu options.
		    Menu edit = new Menu("Edit");
		    MenuItem properties = new MenuItem("Properties");
		    edit.getItems().add(properties);
		     
		    //Create and add the "Help" sub-menu options.
		    Menu help = new Menu("Help");
		    MenuItem visitWebsite = new MenuItem("Visit Website");
		    help.getItems().add(visitWebsite);
		     
		    mainMenu.getMenus().addAll(file, edit, help);
		    vbox.getChildren().add(mainMenu);
		    vbox.getChildren().add(toolBar);
		        
		    return vbox;
		}
		    
		/*
		 * Creates a VBox with a list of links for the left region
		 */
		    private VBox addVBox() {
		        
		        VBox vbox = new VBox();
		        vbox.setPadding(new Insets(10)); // Set all sides to 10
		        vbox.setSpacing(8);              // Gap between nodes
		 
		        Text title = new Text("Data");
		        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		        vbox.getChildren().add(title);
		        
		        Hyperlink options[] = new Hyperlink[] {
		            new Hyperlink("Sales"),
		            new Hyperlink("Marketing"),
		            new Hyperlink("Distribution"),
		            new Hyperlink("Costs")};
		 
		        for (int i=0; i<4; i++) {
		            // Add offset to left side to indent from title
		            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
		            vbox.getChildren().add(options[i]);
		        }
		        
		        return vbox;
		    }
		 
		/*
		 * Uses a stack pane to create a help icon and adds it to the right side of an HBox
		 * 
		 * @param hb HBox to add the stack to
		 */
		private void addStackPane(HBox hb) {
		 
		        StackPane stack = new StackPane();
		        Rectangle helpIcon = new Rectangle(30.0, 25.0);
		        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
		            new Stop[]{
		            new Stop(0,Color.web("#4977A3")),
		            new Stop(0.5, Color.web("#B0C6DA")),
		            new Stop(1,Color.web("#9CB6CF")),}));
		        helpIcon.setStroke(Color.web("#D0E6FA"));
		        helpIcon.setArcHeight(3.5);
		        helpIcon.setArcWidth(3.5);
		        
		        Text helpText = new Text("?");
		        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		        helpText.setFill(Color.WHITE);
		        helpText.setStroke(Color.web("#7080A0")); 
		        
		        stack.getChildren().addAll(helpIcon, helpText);
		        stack.setAlignment(Pos.CENTER_RIGHT);
		        // Add offset to right for question mark to compensate for RIGHT 
		        // alignment of all nodes
		        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));
		        
		        hb.getChildren().add(stack);
		        HBox.setHgrow(stack, Priority.ALWAYS);
		                
		    }
		 
		private Accordion addLeftPane() {
			final Accordion accordion = new Accordion();

			TitledPane tp =new TitledPane();
			tp.setText("Title");

			TreeItem<String>rootItem =new TreeItem<String>("Inbox");
			rootItem.setExpanded(true);
			for(int i=1;i<6;i++) {
			TreeItem<String> item=new TreeItem<String>("Message"+i);
			rootItem.getChildren().add(item);
			}
			TreeView<String> tree = new TreeView<String>(rootItem);
			tree.setEditable(true);
			tree.setShowRoot(false);
			tree.setCellFactory((TreeView<String> p) ->
			new TextFieldTreeCellImpl());
			tp.setContent(tree);

			TitledPane tp2 = new TitledPane();
			tp2.setText("Resource");
			TreeItem<String> rootItem2 = new TreeItem<String>("Inbox");
			rootItem2.setExpanded(true);
			for (int i=1;i<6;i++) {
			TreeItem<String> item2=new TreeItem<String>("Message"+i);
			rootItem2.getChildren().add(item2);
			}
			TreeView<String> tree2 = new TreeView<String>(rootItem2);
			tp2.setContent(tree2);

			accordion.getPanes().add(tp);
			accordion.getPanes().add(tp2);
			accordion.setExpandedPane(tp);
			
			return accordion;
		}


	public static void main(String[] args) {
		launch(args);
	}
}
