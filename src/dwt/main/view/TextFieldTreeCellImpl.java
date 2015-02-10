1package dwt.main.view;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
	public class TextFieldTreeCellImpl extends TreeCell<String> {
		 
        private TextField textField;
        private final ContextMenu addMenu = new ContextMenu();
 
        public TextFieldTreeCellImpl() {
	        	setOnDragDetected(new EventHandler<MouseEvent>(){
	        	@Override
	        	public void handle(MouseEvent event) {
	        		Dragboard dragBoard = startDragAndDrop(TransferMode.MOVE);
	        		ClipboardContent content = new ClipboardContent();
	        		content.put(DataFormat.PLAIN_TEXT, getTreeItem().toString());
	        		dragBoard.setContent(content);
	        		event.consume();
	        	}
	        });

	        	setOnDragDone(new EventHandler<DragEvent>() {
	        	@Override
	        	public void handle(DragEvent dragEvent) {
	        		dragEvent.consume();
	        	}
        	});

	        	setOnDragEntered(new EventHandler<DragEvent>() {
	        	@Override
	        	public void handle(DragEvent dragEvent) {
	        		dragEvent.consume();
	        	}
        	});

	        	setOnDragOver(new EventHandler<DragEvent>() {
	        	@Override
	        	public void handle(DragEvent dragEvent) {
	        		if(dragEvent.getDragboard().hasString()) {
	        			String valueToMove = dragEvent.getDragboard().getString();
	        			if(!valueToMove.matches(getTreeItem().toString())) {
	        				dragEvent.acceptTransferModes(TransferMode.MOVE);
	        			}
	        		}
	        		dragEvent.consume();
	        	}
	        });

        		setOnDragExited(new EventHandler<DragEvent>() {
        		@Override
        		public void handle(DragEvent dragEvent) {
        			dragEvent.consume();
        		}
        	});

        		setOnDragDropped(new EventHandler<DragEvent>() {
        		@Override
        		public void handle(DragEvent dragEvent) {
        			///dragEvent.getGestureSource().getParent().getChildren());
        			dragEvent.consume();
        		}
        	});

        	
            MenuItem addMenuItem = new MenuItem("Add Employee");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction((ActionEvent t) -> {
            	TreeItem newEmployee = new TreeItem<>("New Employee");
            	
            	if (getTreeItem() == null) {
            		getTreeView().getRoot().getChildren().add(newEmployee);
            	} else {
            		getTreeItem().getChildren().add(newEmployee);
            	}
               
            });
        }
 
        @Override
        public void startEdit() {
            super.startEdit();
 
            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
                setContextMenu(addMenu);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
//                    if (
//                        !getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
//                    ){
                        setContextMenu(addMenu);
//                    }
                }
            }
        }
        
        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased((KeyEvent t) -> {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(textField.getText());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            });  
            
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

