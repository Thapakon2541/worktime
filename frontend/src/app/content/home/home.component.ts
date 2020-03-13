import { ComponentType } from "@angular/cdk/portal";
import { Component, OnInit } from "@angular/core";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { NgxSpinnerService } from "ngx-spinner";
import { LayoutConstants } from "src/app/shared/constants/LayoutConstants";
//component
import { OvertimeworkformComponent } from "../overtimeworkform/overtimeworkform.component";
import { SideworkformComponent } from "../sideworkform/sideworkform.component";
import { SideWork } from "src/app/shared/interfaces/sidework";
import { SideworkService } from "src/app/shared/service/sidework.service";
import { finalize } from "rxjs/operators";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
  providers: []
})
export class HomeComponent implements OnInit {
  cdgImagePath: string = LayoutConstants.cdgImagePath;
  dataSideworkTime;
  menu: { title: string; img: string; overlay: ComponentType<any> }[] = [
    {
      title: "ทำงานนอกสถานที่",
      img: LayoutConstants.sideWorkImagePath,
      overlay: SideworkformComponent
    },
    {
      title: "ทำงานล่วงเวลา",
      img: LayoutConstants.overtimeImagePath,
      overlay: OvertimeworkformComponent
    },
    {
      title: "ประวัติการลงเวลา",
      img: LayoutConstants.historyImagePath,
      overlay: OvertimeworkformComponent
    }
  ];
  constructor(
    public dialog: MatDialog,
    private sideWorkService: SideworkService
  ) {}

  ngOnInit(): void {}

  openDialog(overlay: ComponentType<unknown>): void {
    const empNo = localStorage.getItem("employeeId");
    this.sideWorkService.getSideWorkOnDay(empNo, new Date()).subscribe(res => {
      this.dataSideworkTime = res.data;
      console.log(res.data);
      const configDialog: MatDialogConfig<any> = {
        disableClose: true,
        autoFocus: false,
        data: this.dataSideworkTime
      };
      const dialogRef = this.dialog.open(overlay, configDialog);

      dialogRef.afterClosed().subscribe(result => {
        console.log(result);
      });
    });
  }
}
