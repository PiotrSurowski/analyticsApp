import {Component, OnInit, ViewChild} from '@angular/core';
import {AppService} from "./app.service";
import {MatTableDataSource} from "@angular/material/table";
import {Result} from "./result";
import {Chart, ChartConfiguration, ChartData, ChartDataset, ChartEvent, ChartOptions, ChartType} from 'chart.js';
import {BaseChartDirective} from "ng2-charts";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'analyticsView';
  public trainError: number[] = [];
  public displayedColumns = ['wynik','dane_wejsciowe'];
  public dataSource = new MatTableDataSource<Result>();

  constructor(private appService: AppService) {
  }

  public lineChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: [ ],
        label: 'Series C',
        yAxisID: 'y-axis-1',
        backgroundColor: 'rgba(255,0,0,0.3)',
        borderColor: 'red',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)',
        fill: 'origin',
      }
    ],
    labels: []
  };

  private generateNumber(): number {
    return Math.floor(Math.random());
  }

  public randomize(): void {
    for (let i = 0; i < this.lineChartData.datasets.length; i++) {
      for (let j = 0; j < this.trainError.length; j++) {
        this.lineChartData.datasets[i].data[j] = this.getTrainResult(j);
        this.lineChartData.labels?.push(j);
      }
    }
    this.chart?.update();
  }

  public lineChartType: ChartType = 'line';
  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;

  ngOnInit(): void {
    this.getData();
  }

  public getData(){
    this.appService.getClasification().subscribe((data: Result[]) => {
      this.dataSource.data = data;
    });
  }

  public beginTraining(): void{
    this.appService.beginTraining().subscribe((data: number[]) => {
      for (let i=0; i < data.length; i++){
        this.trainError.push(data[i]);
      }
    });
  }

  public getTrainResult(i: number): number{
    return this.trainError[i];
  }
}
