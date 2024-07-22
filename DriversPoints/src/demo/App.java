package demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelos.Driver;
import repositorios.DriverRepository;

import java.util.List;

public class App extends Application {

    private DriverRepository driverRepository = new DriverRepository();
    private ObservableList<Driver> driversData = FXCollections.observableArrayList();
    private TableView<Driver> driverTableView = new TableView<>();
    private Button loadDriversButton = new Button("Cargar Conductores");

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(loadDriversButton, driverTableView);
        Scene scene = new Scene(vbox, 800, 600);
        
        configureDriversTableView();

        loadDriversButton.setOnAction(event -> {
            loadDrivers();
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Conductores Ordenados por Puntos");
        primaryStage.show();
    }

    private void configureDriversTableView() {
        TableColumn<Driver, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("driverId"));

        
        TableColumn<Driver, String> forenameColumn = new TableColumn<>("Nombre");
        forenameColumn.setCellValueFactory(new PropertyValueFactory<>("forename"));

        TableColumn<Driver, String> surnameColumn = new TableColumn<>("Apellido");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

  

        TableColumn<Driver, String> urlColumn = new TableColumn<>("URL");
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));

        TableColumn<Driver, Integer> totalPointsColumn = new TableColumn<>("Puntos");
        totalPointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

        driverTableView.getColumns().addAll(idColumn, forenameColumn, surnameColumn, urlColumn, totalPointsColumn);
    }

    private void loadDrivers() {
        List<Driver> drivers = driverRepository.getDriversOrderedByPoints();
        driversData.clear();
        driversData.addAll(drivers);
        driverTableView.setItems(driversData);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
