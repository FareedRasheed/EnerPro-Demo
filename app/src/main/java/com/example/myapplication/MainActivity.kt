package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val listOfAudits = ArrayList<String>()
        listOfAudits.add("Audit 1 - Description 1 - Date 1 - Client 1")
        listOfAudits.add("Audit 2 - Description 2 - Date 2 - Client 2")
        listOfAudits.add("Audit 3 - Description 3 - Date 3 - Client 3")

        val listOfBuildings = ArrayList<String>()
        listOfBuildings.add("Building 1")
        listOfBuildings.add("Building 2")
        listOfBuildings.add("Building 3")

        val listOfFloors = ArrayList<String>()
        listOfFloors.add("Floor 1")
        listOfFloors.add("Floor 2")
        listOfFloors.add("Floor 3")

        val equipmentList = ArrayList<String>()
        equipmentList.add("Equipment 1")
        equipmentList.add("Equipment 2")
        equipmentList.add("Equipment 3")




        startScreen(listOfAudits, listOfBuildings, listOfFloors,equipmentList)
    }

    private fun startScreen(
        listOfAudits: ArrayList<String>,
        listOfBuildings: ArrayList<String>,
        listOfFloors: ArrayList<String>,
        equipmentList: ArrayList<String>
    ) {
        setContentView(R.layout.start_screen)
        val startButton: Button = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            selectAuditScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }


    }

    private fun addAuditScreen(listOfAudits: ArrayList<String>, listOfBuildings: ArrayList<String>, listOfFloors: ArrayList<String>, equipmentList: ArrayList<String>) {
        setContentView(R.layout.add_audit_screen)

        val addAuditCancelButton: Button = findViewById(R.id.AddAuditCancel)
        addAuditCancelButton.setOnClickListener {
            selectAuditScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }

        val editTextAuditName: EditText = findViewById(R.id.editTextAuditName)
        val editTextAuditDescription: EditText = findViewById(R.id.editTextAuditDescription)
        val editTextDate: EditText = findViewById(R.id.editTextDate)
        val editTextClient: EditText = findViewById(R.id.editTextClient)
        val addAuditSaveButton: Button = findViewById(R.id.AddAuditSave)
        addAuditSaveButton.setOnClickListener {
            val nameInput = editTextAuditName.text.toString()
            val descInput = editTextAuditDescription.text.toString()
            val dateInput = editTextDate.text.toString()
            val clientInput = editTextClient.text.toString()

            val newAudit = "$nameInput - $descInput - $dateInput - $clientInput"
            listOfAudits.add(newAudit)

            selectAuditScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)


        }
    }

    private fun selectAuditScreen(
        listOfAudits: ArrayList<String>,
        listOfBuildings: ArrayList<String>,
        listOfFloors: ArrayList<String>,
        equipmentList: ArrayList<String>
    ) {

        setContentView(R.layout.select_audit_screen)
        val cancelSelectAuditButton: Button = findViewById(R.id.cancelSelectAuditButton)
        cancelSelectAuditButton.setOnClickListener {
            startScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }

        val listView: ListView = findViewById(R.id.listAuditsView)


        val adapter = ArrayAdapter(this, R.layout.list_item, R.id.listItemTextView, listOfAudits)

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val clickedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(this, "Clicked on item: $clickedItem", Toast.LENGTH_SHORT).show()
            addBuildingScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val addAuditButton: Button = findViewById(R.id.addAuditButton)
        addAuditButton.setOnClickListener {
            addAuditScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
    }

    private fun addBuildingScreen(listOfAudits: ArrayList<String>, listOfBuildings: ArrayList<String>, listOfFloors: ArrayList<String>,equipmentList: ArrayList<String>) {
        setContentView(R.layout.add_buildings_screen)

        val listView: ListView = findViewById(R.id.listBuildingsView)
        val adapter = ArrayAdapter(this, R.layout.list_item, R.id.listItemTextView, listOfBuildings)

        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val clickedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(this, "Clicked on item: $clickedItem", Toast.LENGTH_SHORT).show()
            selectFloorScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }


        val editTextBuildingName: EditText = findViewById(R.id.editTextAddBuilding)
        val addBuildingButton: Button = findViewById(R.id.addBuildingButton)
        addBuildingButton.setOnClickListener {
            listOfBuildings.add(editTextBuildingName.text.toString())
            adapter.notifyDataSetChanged()
        }

        val buildingSelectCancelButton: Button = findViewById(R.id.BuildingSelectCancelButton)
        buildingSelectCancelButton.setOnClickListener {
            selectAuditScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }

    }
    private fun selectFloorScreen(
        listOfAudits: ArrayList<String>,
        listOfBuildings: ArrayList<String>,
        listOfFloors: ArrayList<String>,
        equipmentList: ArrayList<String>
    ) {
        setContentView(R.layout.select_floor_screen)

        val listView: ListView = findViewById(R.id.floorListView)
        val adapter = ArrayAdapter(this, R.layout.list_item, R.id.listItemTextView, listOfFloors)

        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val clickedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(this, "Clicked on item: $clickedItem", Toast.LENGTH_SHORT).show()
            noteScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)


        }

        val cancelAddFloorButton: Button = findViewById(R.id.cancelAddFloorButton)
        cancelAddFloorButton.setOnClickListener{
            addBuildingScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val addFloorButton: Button = findViewById(R.id.addFloorButton)
        addFloorButton.setOnClickListener{
            addFloorScreen(listOfAudits, listOfBuildings, listOfFloors,equipmentList)
        }

    }



    private fun addFloorScreen(
        listOfAudits: ArrayList<String>,
        listOfBuildings: ArrayList<String>,
        listOfFloors: ArrayList<String>,
        equipmentList: ArrayList<String>
    ) {
        setContentView(R.layout.add_floor_screen)

        val editTextFloorNumber: EditText = findViewById(R.id.editTextFloorNumber)
        val editTextFloorName: EditText = findViewById(R.id.editTextFloorName)

        val cancelAddFloorButton: Button = findViewById(R.id.CancelAddFloorButton)
        cancelAddFloorButton.setOnClickListener{
            selectFloorScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val saveFloorButton: Button = findViewById(R.id.SaveFloorButton)
        saveFloorButton.setOnClickListener{
            val floorNumber = editTextFloorNumber.text.toString()
            val floorName = editTextFloorName.text.toString()
            val newFloor = "$floorNumber - $floorName"
            listOfFloors.add(newFloor)
            selectFloorScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val uploadFloorPlanButton: Button = findViewById(R.id.UploadFloorPlanButton)
        uploadFloorPlanButton.setOnClickListener{
            Toast.makeText(this, "Floor Plan Uploaded", Toast.LENGTH_SHORT).show()
        }

    }
    private fun noteScreen(listOfAudits: ArrayList<String>, listOfBuildings: ArrayList<String>, listOfFloors: ArrayList<String>,equipmentList: ArrayList<String>) {
        setContentView(R.layout.notes_screen)
        
        val cancelNotesButton: Button = findViewById(R.id.cancelNotesButton)
        cancelNotesButton.setOnClickListener {
            selectFloorScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val viewEquipmentButton: Button = findViewById(R.id.viewEquipmentButton)
        viewEquipmentButton.setOnClickListener {
            viewEquipmentScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val viewNotesButton: Button = findViewById(R.id.viewNotesButton)
        viewNotesButton.setOnClickListener {
            Toast.makeText(this, "Notes Viewed", Toast.LENGTH_SHORT).show()
        }



    }

    private fun viewEquipmentScreen(
        listOfAudits: ArrayList<String>,
        listOfBuildings: ArrayList<String>,
        listOfFloors: ArrayList<String>,
        equipmentList: ArrayList<String>
    ) {
        setContentView(R.layout.view_equipment_screen)

        val cancelViewEquipmentButton: Button = findViewById(R.id.cancelViewEquipmentButton)
        cancelViewEquipmentButton.setOnClickListener {
            noteScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val addEquipmentButton: Button = findViewById(R.id.addEquipmentButton)
        addEquipmentButton.setOnClickListener {
            addEquipmentScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val generateEquipmentAnalysisButton: Button = findViewById(R.id.generateEquipmentAnalysisButton)
        generateEquipmentAnalysisButton.setOnClickListener {
            Toast.makeText(this, "Equipment Analysis Generated", Toast.LENGTH_SHORT).show()
        }

        val listView: ListView = findViewById(R.id.EquipmentListView)
        val adapter = ArrayAdapter(this, R.layout.list_item, R.id.listItemTextView, equipmentList)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val clickedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(this, "Clicked on item: $clickedItem", Toast.LENGTH_SHORT).show()
        }



    }

    private fun addEquipmentScreen(
        listOfAudits: ArrayList<String>,
        listOfBuildings: ArrayList<String>,
        listOfFloors: ArrayList<String>,
        equipmentList: ArrayList<String>
    ) {
        setContentView(R.layout.add_equipment_screen)
        val cancelAddEquipmentButton: Button = findViewById(R.id.cancelAddEquipmentButton)
        cancelAddEquipmentButton.setOnClickListener {
            viewEquipmentScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val uploadEquipmentButton: Button = findViewById(R.id.uploadEquipmentButton)
        uploadEquipmentButton.setOnClickListener {
            Toast.makeText(this, "Equipment Uploaded", Toast.LENGTH_SHORT).show()
        }
        val uploadNameTagButton: Button = findViewById(R.id.uploadNameTagButton)
        uploadNameTagButton.setOnClickListener {
            Toast.makeText(this, "Name Tag Uploaded", Toast.LENGTH_SHORT).show()
        }
        val autoFillEquipmentButton: Button = findViewById(R.id.autoFillEquipmentButton)
        autoFillEquipmentButton.setOnClickListener {
            Toast.makeText(this, "Equipment Autofilled", Toast.LENGTH_SHORT).show()
        }
        val manualFillEquipmentButton: Button = findViewById(R.id.ManualFillEquipmentButton)
        manualFillEquipmentButton.setOnClickListener {
            Toast.makeText(this, "Equipment Manually Filled", Toast.LENGTH_SHORT).show()
            manualFillEquipmentScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)

        }
    }

    private fun manualFillEquipmentScreen(
        listOfAudits: ArrayList<String>,
        listOfBuildings: ArrayList<String>,
        listOfFloors: ArrayList<String>,
        equipmentList: ArrayList<String>
    ) {
        setContentView(R.layout.manual_fill_equipment_screen)


        val editTextEquipmentName: EditText = findViewById(R.id.editTextEquipmentName)
        val editTextSerialNumber: EditText = findViewById(R.id.editTexAddEquipmentSerialNumber)
        val editTextCRN: EditText = findViewById(R.id.editTextAddEquipmentCRN)
        val editTextDate: EditText = findViewById(R.id.editTextAddEquipmentDate)



        val cancelAddEquipmentButton: Button = findViewById(R.id.CancelAddEquipmentButton)
        cancelAddEquipmentButton.setOnClickListener {
            viewEquipmentScreen(listOfAudits, listOfBuildings, listOfFloors, equipmentList)
        }
        val addEquipmentButton: Button = findViewById(R.id.AddEquipmentButton)
        addEquipmentButton.setOnClickListener {

            val equipmentName= editTextEquipmentName.text.toString()
            val serialNumber= editTextSerialNumber.text.toString()
            val crn= editTextCRN.text.toString()
            val date= editTextDate.text.toString()

            equipmentList.add("$equipmentName - $serialNumber - $crn - $date")
            viewEquipmentScreen(listOfAudits, listOfBuildings, listOfFloors,equipmentList)

        }
    }
}


