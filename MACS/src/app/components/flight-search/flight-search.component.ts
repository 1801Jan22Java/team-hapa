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

  destinations = ["Kabul", "Algiers", "Oran",
    "Luanda", "Buenos Aires", "Córdoba",
    "Rosario", "Yerevan", "Melbourne", "Brisbane",
    "Vienna", "Baku", "Dhaka", "Chittagong", "Minsk",
    "Santa Cruz de la Sierra", "Sao Paulo", "Rio de Janeiro",
    "Salvador", "Fortaleza", "Brasilia", "Belo Horizonte",
    "Curitiba", "Recife", "Porto Alegre", "Campinas", "Sofia",
    "Ouagadougou", "Phnom Penh", "Douala", "Yaounde", "Toronto",
    "Montreal", "Calgary", "Santiago", "Chongqing", "Shanghai",
    "Beijing", "Guangzhou", "Tianjin", "Wenzhou", "Xi'an", "Shenzhen",
    "Suzhou", "Nanjing", "Dongguan", "Quanzhou", "Shenyang",
    "Hong Kong", "Fuzhou", "Changsha", "Wuhan", "Qingdao", "Foshan",
    "Zunyi", "Shantou", "Chengdu", "Shijiazhuang", "Harbin",
    "Zhengzhou", "Changchun", "Hangzhou", "Xiamen", "Ningbo", "Hefei",
    "Tangshan", "Zhongshan", "Chaozhou", "Lanzhou", "Dalian", "Jinan",
    "Bogota", "Medellin", "Cali", "Barranquilla", "Brazzaville", "Havana",
    "Prague", "Kinshasa", "Guayaquil", "Quito", "Cairo", "Alexandria",
    "Giza", "Addis Ababa", "Paris", "T'bilisi", "Berlin", "Hamburg",
    "Munich", "Cologne", "Accra", "Guatemala City", "Budapest",
    "Delhi", "Mumbai", "Hyderabad", "Chennai", "Bengaluru", "Ahmedabad",
    "Kolkata", "Surat", "Pune", "Jaipur", "Lucknow", "Kanpur",
    "Nagpur", "Visakhapatnam", "Bhopal", "Patna", "Vijayawada",
    "Jakarta", "Surabaya", "Bandung", "Medan", "Palembang",
    "Semarang", "Makassar", "Tehran", "Mashhad", "Isfahan",
    "Karaj", "Shiraz", "Tabriz", "Baghdad", "Basra", "Rome",
    "Milan", "Abidjan", "Tokyo", "Yokohama", "Osaka", "Nagoya"
    , "Sapporo", "Kobe", "Kawasaki", "Fukuoka", "Kyoto", "Saitama",
    "Hiroshima", "Almaty", "Astana", "Nairobi", "Pyongyang", "Seoul",
    "Busan", "Incheon", "Daegu", "Daejeon", "Kwangju", "Ulsan", "Tripoli",
    "Kuala Lumpur", "Mexico City", "Tijuana", "Guadalajara", "Monterrey",
    "Casablanca", "Fez", "Maputo", "Yangon", "Mandalay", "Kathmandu", "Auckland",
    "Managua", "Lagos", "Kano", "Ibadan", "Abuja", "Karachi", "Lahore", "Faisalabad",
    "Rawalpindi", "Gujranwala", "Peshawar", "Hyderabad", "Islamabad", "Lima", "Quezon City",
    "Manila", "Davao City", "Caloocan", "Warsaw", "Bucharest", "Moscow",
    "Saint Petersburg", "Novosibirsk", "Yekaterinburg", "Nizhny Novgorod", "Omsk",
    "Rostov-on-Don", "Riyadh", "Jeddah", "Dakar", "Belgrade", "Singapore", "Johannesburg",
    "Cape Town", "Durban", "Ekurhuleni", "Suwon", "Madrid", "Barcelona", "Khartoum",
    "New Taipei City", "Kaohsiung", "Taichung", "Taipei", "Tainan", "Dar es Salaam",
    "Bangkok", "Tunis", "Istanbul", "Ankara", "Izmir", "Kampala", "Kiev", "Kharkiv",
    "Dubai", "Abu Dhabi", "London", "Birmingham", "New York City", "Los Angeles",
    "Chicago", "Houston", "Philadelphia", "Phoenix", "San Antonio", "San Diego",
    "Dallas", "Montevideo", "Tashkent", "Caracas", "Maracaibo", "Ho Chi Minh City", "Hanoi",
    "Sana'a", "Lusaka", "Harare", "Bulawayo"]
}
