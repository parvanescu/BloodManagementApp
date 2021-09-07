import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { DoctorComponent } from './doctor/doctor.component';
import { DrugComponent } from './drug/drug.component';
import { HospitalComponent } from './hospital/hospital.component';
import { PersonComponent } from './person/person.component';
import { PrescriptionComponent } from './prescription/prescription.component';
import { TransfusionComponent } from './transfusion/transfusion.component';
import { AddDoctorComponent } from './doctor/add-doctor/add-doctor.component';
import { UpdateDoctorComponent } from './doctor/update-doctor/update-doctor.component';
import { DeleteDoctorComponent } from './doctor/delete-doctor/delete-doctor.component';
import { GetDoctorComponent } from './doctor/get-doctor/get-doctor.component';
import { GetAllDoctorsComponent } from './doctor/get-all-doctors/get-all-doctors.component';
import { FilterDoctorComponent } from './doctor/filter-doctor/filter-doctor.component';
import { ReportDoctorComponent } from './doctor/report-doctor/report-doctor.component';
import { AddDrugComponent } from './drug/add-drug/add-drug.component';
import { DeleteDrugComponent } from './drug/delete-drug/delete-drug.component';
import { GetDrugComponent } from './drug/get-drug/get-drug.component';
import { UpdateDrugComponent } from './drug/update-drug/update-drug.component';
import { GetAllDrugsComponent } from './drug/get-all-drugs/get-all-drugs.component';
import { FilterDrugComponent } from './drug/filter-drug/filter-drug.component';
import { ReportDrugComponent } from './drug/report-drug/report-drug.component';
import { AddHospitalComponent } from './hospital/add-hospital/add-hospital.component';
import { UpdateHospitalComponent } from './hospital/update-hospital/update-hospital.component';
import { DeleteHospitalComponent } from './hospital/delete-hospital/delete-hospital.component';
import { GetHospitalComponent } from './hospital/get-hospital/get-hospital.component';
import { GetAllHospitalsComponent } from './hospital/get-all-hospitals/get-all-hospitals.component';
import { FilterHospitalComponent } from './hospital/filter-hospital/filter-hospital.component';
import { ReportHospitalComponent } from './hospital/report-hospital/report-hospital.component';
import { AddPersonComponent } from './person/add-person/add-person.component';
import { UpdatePersonComponent } from './person/update-person/update-person.component';
import { DeletePersonComponent } from './person/delete-person/delete-person.component';
import { GetPersonComponent } from './person/get-person/get-person.component';
import { GetAllPersonsComponent } from './person/get-all-persons/get-all-persons.component';
import { FilterPersonComponent } from './person/filter-person/filter-person.component';
import { AddPrescriptionComponent } from './prescription/add-prescription/add-prescription.component';
import { DeletePrescriptionComponent } from './prescription/delete-prescription/delete-prescription.component';
import { UpdatePrescriptionComponent } from './prescription/update-prescription/update-prescription.component';
import { GetPrescriptionComponent } from './prescription/get-prescription/get-prescription.component';
import { GetAllPrescriptionsComponent } from './prescription/get-all-prescriptions/get-all-prescriptions.component';
import { FilterPrescriptionComponent } from './prescription/filter-prescription/filter-prescription.component';
import { ReportPrescriptionComponent } from './prescription/report-prescription/report-prescription.component';
import { AddTransfusionComponent } from './transfusion/add-transfusion/add-transfusion.component';
import { UpdateTransfusionComponent } from './transfusion/update-transfusion/update-transfusion.component';
import { DeleteTransfusionComponent } from './transfusion/delete-transfusion/delete-transfusion.component';
import { GetTransfusionComponent } from './transfusion/get-transfusion/get-transfusion.component';
import { GetAllTransfusionsComponent } from './transfusion/get-all-transfusions/get-all-transfusions.component';
import { FilterTransfusionComponent } from './transfusion/filter-transfusion/filter-transfusion.component';
import { ReportTransfusionComponent } from './transfusion/report-transfusion/report-transfusion.component';
import { ReportPersonComponent } from './person/report-person/report-person.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DoctorService} from './doctor/shared/doctor.service';
import {DrugService} from './drug/shared/drug.service';
import {HospitalService} from './hospital/shared/hospital.service';
import {PersonService} from './person/shared/person.service';
import {PrescriptionService} from './prescription/shared/prescription.service';
import {TransfusionService} from './transfusion/shared/transfusion.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
import {DatePipe} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DoctorComponent,
    DrugComponent,
    HospitalComponent,
    PersonComponent,
    PrescriptionComponent,
    TransfusionComponent,
    AddDoctorComponent,
    UpdateDoctorComponent,
    DeleteDoctorComponent,
    GetDoctorComponent,
    GetAllDoctorsComponent,
    FilterDoctorComponent,
    ReportDoctorComponent,
    AddDrugComponent,
    DeleteDrugComponent,
    GetDrugComponent,
    UpdateDrugComponent,
    GetAllDrugsComponent,
    FilterDrugComponent,
    ReportDrugComponent,
    AddHospitalComponent,
    UpdateHospitalComponent,
    DeleteHospitalComponent,
    GetHospitalComponent,
    GetAllHospitalsComponent,
    FilterHospitalComponent,
    ReportHospitalComponent,
    AddPersonComponent,
    UpdatePersonComponent,
    DeletePersonComponent,
    GetPersonComponent,
    GetAllPersonsComponent,
    FilterPersonComponent,
    AddPrescriptionComponent,
    DeletePrescriptionComponent,
    UpdatePrescriptionComponent,
    GetPrescriptionComponent,
    GetAllPrescriptionsComponent,
    FilterPrescriptionComponent,
    ReportPrescriptionComponent,
    AddTransfusionComponent,
    UpdateTransfusionComponent,
    DeleteTransfusionComponent,
    GetTransfusionComponent,
    GetAllTransfusionsComponent,
    FilterTransfusionComponent,
    ReportTransfusionComponent,
    ReportPersonComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatTableModule
    ],
  providers: [DoctorService, DrugService, HospitalService, PersonService, PrescriptionService, TransfusionService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
