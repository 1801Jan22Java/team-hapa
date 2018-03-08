import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FlightSearchService } from '../../services/flight-search/flight-search.service';

@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css']
})
export class FlightSearchComponent implements OnInit {

  constructor(private router: Router, private service: FlightSearchService) {

  }

  valuedate = new Date();

  form = new FormGroup({
    date: new FormControl("", Validators.required),
    destination: new FormControl("", Validators.required)
  })


  ngOnInit() {
  }

  onSubmit(): void {
    let date = this.form.get("date").value;
    let destination = this.form.get("destination").value;

    this.service.getFlightResults(date, destination);

    this.router.navigateByUrl('flightsearch')
  }

  destinations = ["Abidjan","Abu Dhabi","Abuja","Accra","Addis Ababa","Ahmedabad","Alexandria","Algiers","Almaty","Ankara","Astana","Auckland","Baghdad","Baku","Bandung","Bangkok","Barcelona","Barranquilla","Basra","Beijing","Belgrade","Belo Horizonte","Bengaluru","Berlin","Bhopal","Birmingham","Bogota","Brasilia","Brazzaville","Brisbane","Bucharest","Budapest","Buenos Aires","Bulawayo","Busan","Cairo","Calgary","Cali","Caloocan","Campinas","Cape Town","Caracas","Casablanca","Changchun","Changsha","Chaozhou","Chengdu","Chennai","Chicago","Chittagong","Chongqing","Cologne","Curitiba","Córdoba","Daegu","Daejeon","Dakar","Dalian","Dallas","Dar es Salaam","Davao City","Delhi","Dhaka","Dongguan","Douala","Dubai","Durban","Ekurhuleni","Faisalabad","Fez","Fortaleza","Foshan","Fukuoka","Fuzhou","Giza","Guadalajara","Guangzhou","Guatemala City","Guayaquil","Gujranwala","Hamburg","Hangzhou","Hanoi","Harare","Harbin","Havana","Hefei","Hiroshima","Ho Chi Minh City","Hong Kong","Houston","Hyderabad","Hyderabad","Ibadan","Incheon","Isfahan","Islamabad","Istanbul","Izmir","Jaipur","Jakarta","Jeddah","Jinan","Johannesburg","Kabul","Kampala","Kano","Kanpur","Kaohsiung","Karachi","Karaj","Kathmandu","Kawasaki","Kharkiv","Khartoum","Kiev","Kinshasa","Kobe","Kolkata","Kuala Lumpur","Kwangju","Kyoto","Lagos","Lahore","Lanzhou","Lima","London","Los Angeles","Luanda","Lucknow","Lusaka","Madrid","Makassar","Managua","Mandalay","Manila","Maputo","Maracaibo","Mashhad","Medan","Medellin","Melbourne","Mexico City","Milan","Minsk","Monterrey","Montevideo","Montreal","Moscow","Mumbai","Munich","Nagoya","Nagpur","Nairobi","Nanjing","New Taipei City","New York City","Ningbo","Nizhny Novgorod","Novosibirsk","Omsk","Oran","Osaka","Ouagadougou","Palembang","Paris","Patna","Peshawar","Philadelphia","Phnom Penh","Phoenix","Porto Alegre","Prague","Pune","Pyongyang","Qingdao","Quanzhou","Quezon City","Quito","Rawalpindi","Recife","Rio de Janeiro","Riyadh","Rome","Rosario","Rostov-on-Don","Saint Petersburg","Saitama","Salvador","San Antonio","San Diego","Sana'a","Santa Cruz de la Sierra","Santiago","Sao Paulo","Sapporo","Semarang","Seoul","Shanghai","Shantou","Shenyang","Shenzhen","Shijiazhuang","Shiraz","Singapore","Sofia","Surabaya","Surat","Suwon","Suzhou","T'bilisi","Tabriz","Taichung","Tainan","Taipei","Tangshan","Tashkent","Tehran","Tianjin","Tijuana","Tokyo","Toronto","Tripoli","Tunis","Ulsan","Vienna","Vijayawada","Visakhapatnam","Warsaw","Wenzhou","Wuhan","Xi'an","Xiamen","Yangon","Yaounde","Yekaterinburg","Yerevan","Yokohama","Zhengzhou","Zhongshan","Zunyi"]
}
