import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {CommonModule} from '@angular/common';

import {CustomMaterialModule} from '../CustomMaterial/custom-material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CdkTableModule} from '@angular/cdk/table';
import {MatDatepickerModule, MatNativeDateModule, MatProgressSpinnerModule, MatSliderModule, MatSnackBarModule} from '@angular/material';
import {TableComponent} from './table/table.component';
import {DatalakeTableComponent} from './datalake/table/datalake-table.component';
import {DatalakeDataDownloadcomponent} from './datalake/datadownload/datalake-dataDownloadcomponent';
import {NgxChartsModule} from '@swimlane/ngx-charts';
import {LineChartComponent} from './linechart/lineChart.component';
import {DatalakeLineChartComponent} from './datalake/linechart/datalake-lineChart.component';


@NgModule({
    imports: [
        CommonModule,
        FlexLayoutModule,
        CustomMaterialModule,
        ReactiveFormsModule,
        FormsModule,
        CdkTableModule,
        MatSnackBarModule,
        MatProgressSpinnerModule,
        NgxChartsModule,
        MatSliderModule,
        MatDatepickerModule,
        MatNativeDateModule
    ],
    declarations: [
        TableComponent,
        LineChartComponent,
        DatalakeTableComponent,
        DatalakeDataDownloadcomponent,
        DatalakeLineChartComponent,
    ],
    providers: [
        MatDatepickerModule
    ],
    entryComponents: [
    ],
    exports: [
        TableComponent,
        LineChartComponent,
        DatalakeTableComponent,
        DatalakeDataDownloadcomponent,
        DatalakeLineChartComponent
    ]
})
export class CoreUiModule {
}