import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DoctorComponent} from "./doctor/doctor.component";
import {DrugComponent} from "./drug/drug.component";
import {HospitalComponent} from "./hospital/hospital.component";
import {PersonComponent} from "./person/person.component";
import {PrescriptionComponent} from "./prescription/prescription.component";
import {TransfusionComponent} from "./transfusion/transfusion.component";
import {AddDoctorComponent} from "./doctor/add-doctor/add-doctor.component";
import {UpdateDoctorComponent} from "./doctor/update-doctor/update-doctor.component";
import {DeleteDoctorComponent} from "./doctor/delete-doctor/delete-doctor.component";
import {GetDoctorComponent} from "./doctor/get-doctor/get-doctor.component";
import {GetAllDoctorsComponent} from "./doctor/get-all-doctors/get-all-doctors.component";
import {FilterDoctorComponent} from "./doctor/filter-doctor/filter-doctor.component";
import {ReportDoctorComponent} from "./doctor/report-doctor/report-doctor.component";
import {AddDrugComponent} from "./drug/add-drug/add-drug.component";
import {UpdateDrugComponent} from "./drug/update-drug/update-drug.component";
import {DeleteDrugComponent} from "./drug/delete-drug/delete-drug.component";
import {GetDrugComponent} from "./drug/get-drug/get-drug.component";
import {GetAllDrugsComponent} from "./drug/get-all-drugs/get-all-drugs.component";
import {FilterDrugComponent} from "./drug/filter-drug/filter-drug.component";
import {ReportDrugComponent} from "./drug/report-drug/report-drug.component";
import {AddHospitalComponent} from "./hospital/add-hospital/add-hospital.component";
import {UpdateHospitalComponent} from "./hospital/update-hospital/update-hospital.component";
import {DeleteHospitalComponent} from "./hospital/delete-hospital/delete-hospital.component";
import {GetHospitalComponent} from "./hospital/get-hospital/get-hospital.component";
import {GetAllHospitalsComponent} from "./hospital/get-all-hospitals/get-all-hospitals.component";
import {FilterHospitalComponent} from "./hospital/filter-hospital/filter-hospital.component";
import {ReportHospitalComponent} from "./hospital/report-hospital/report-hospital.component";
import {AddPersonComponent} from "./person/add-person/add-person.component";
import {UpdatePersonComponent} from "./person/update-person/update-person.component";
import {DeletePersonComponent} from "./person/delete-person/delete-person.component";
import {GetPersonComponent} from "./person/get-person/get-person.component";
import {GetAllPersonsComponent} from "./person/get-all-persons/get-all-persons.component";
import {FilterPersonComponent} from "./person/filter-person/filter-person.component";
import {ReportPersonComponent} from "./person/report-person/report-person.component";
import {AddPrescriptionComponent} from "./prescription/add-prescription/add-prescription.component";
import {UpdatePrescriptionComponent} from "./prescription/update-prescription/update-prescription.component";
import {DeletePrescriptionComponent} from "./prescription/delete-prescription/delete-prescription.component";
import {GetPrescriptionComponent} from "./prescription/get-prescription/get-prescription.component";
import {GetAllPrescriptionsComponent} from "./prescription/get-all-prescriptions/get-all-prescriptions.component";
import {FilterPrescriptionComponent} from "./prescription/filter-prescription/filter-prescription.component";
import {ReportPrescriptionComponent} from "./prescription/report-prescription/report-prescription.component";
import {AddTransfusionComponent} from "./transfusion/add-transfusion/add-transfusion.component";
import {UpdateTransfusionComponent} from "./transfusion/update-transfusion/update-transfusion.component";
import {DeleteTransfusionComponent} from "./transfusion/delete-transfusion/delete-transfusion.component";
import {GetTransfusionComponent} from "./transfusion/get-transfusion/get-transfusion.component";
import {GetAllTransfusionsComponent} from "./transfusion/get-all-transfusions/get-all-transfusions.component";
import {FilterTransfusionComponent} from "./transfusion/filter-transfusion/filter-transfusion.component";
import {ReportTransfusionComponent} from "./transfusion/report-transfusion/report-transfusion.component";

const routes: Routes = [
  {path: 'doctors', component: DoctorComponent},
  {path: 'doctors/add', component: AddDoctorComponent},
  {path: 'doctors/update', component: UpdateDoctorComponent},
  {path: 'doctors/delete', component: DeleteDoctorComponent},
  {path: 'doctors/get', component: GetDoctorComponent},
  {path: 'doctors/get-all', component: GetAllDoctorsComponent},
  {path: 'doctors/filter', component: FilterDoctorComponent},
  {path: 'doctors/report', component: ReportDoctorComponent},
  {path: 'drugs', component: DrugComponent},
  {path: 'drugs/add', component: AddDrugComponent},
  {path: 'drugs/update', component: UpdateDrugComponent},
  {path: 'drugs/delete', component: DeleteDrugComponent},
  {path: 'drugs/get', component: GetDrugComponent},
  {path: 'drugs/get-all', component: GetAllDrugsComponent},
  {path: 'drugs/filter', component: FilterDrugComponent},
  {path: 'drugs/report', component: ReportDrugComponent},
  {path: 'hospitals', component: HospitalComponent},
  {path: 'hospitals/add', component: AddHospitalComponent},
  {path: 'hospitals/update', component: UpdateHospitalComponent},
  {path: 'hospitals/delete', component: DeleteHospitalComponent},
  {path: 'hospitals/get', component: GetHospitalComponent},
  {path: 'hospitals/get-all', component: GetAllHospitalsComponent},
  {path: 'hospitals/filter', component: FilterHospitalComponent},
  {path: 'hospitals/report', component: ReportHospitalComponent},
  {path: 'persons', component: PersonComponent},
  {path: 'persons/add', component: AddPersonComponent},
  {path: 'persons/update', component: UpdatePersonComponent},
  {path: 'persons/delete', component: DeletePersonComponent},
  {path: 'persons/get', component: GetPersonComponent},
  {path: 'persons/get-all', component: GetAllPersonsComponent},
  {path: 'persons/filter', component: FilterPersonComponent},
  {path: 'persons/report', component: ReportPersonComponent},
  {path: 'prescriptions', component: PrescriptionComponent},
  {path: 'prescriptions/add', component: AddPrescriptionComponent},
  {path: 'prescriptions/update', component: UpdatePrescriptionComponent},
  {path: 'prescriptions/delete', component: DeletePrescriptionComponent},
  {path: 'prescriptions/get', component: GetPrescriptionComponent},
  {path: 'prescriptions/get-all', component: GetAllPrescriptionsComponent},
  {path: 'prescriptions/filter', component: FilterPrescriptionComponent},
  {path: 'prescriptions/report', component: ReportPrescriptionComponent},
  {path: 'transfusions', component: TransfusionComponent},
  {path: 'transfusions/add', component: AddTransfusionComponent},
  {path: 'transfusions/update', component: UpdateTransfusionComponent},
  {path: 'transfusions/delete', component: DeleteTransfusionComponent},
  {path: 'transfusions/get', component: GetTransfusionComponent},
  {path: 'transfusions/get-all', component: GetAllTransfusionsComponent},
  {path: 'transfusions/filter', component: FilterTransfusionComponent},
  {path: 'transfusions/report', component: ReportTransfusionComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
