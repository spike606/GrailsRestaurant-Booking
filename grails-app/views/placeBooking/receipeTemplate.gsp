<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <style>
  .clearfix:after {
    content: "";
    display: table;
    clear: both;
  }

  a {
    color: #5D6975;
    text-decoration: underline;
  }

  body {
    position: relative;
    width: 21cm;
    height: 29.7cm;
    margin: 0 auto;
    color: #001028;
    background: #FFFFFF;
    font-family: Arial, sans-serif;
    font-size: 12px;
    font-family: Arial;
  }

  header {
    padding: 10px 0;
    margin-bottom: 30px;
  }

  #logo {
    text-align: center;
    margin-bottom: 10px;
  }

  #logo img {
    width: 90px;
  }

  h1 {
    border-top: 1px solid  #5D6975;
    border-bottom: 1px solid  #5D6975;
    color: #5D6975;
    font-size: 2.4em;
    line-height: 1.4em;
    font-weight: normal;
    text-align: center;
    margin: 0 0 20px 0;
    background: url(dimension.png);
  }

  #project {
    float: left;
  }

  #project span {
    color: #5D6975;
    text-align: right;
    width: 52px;
    margin-right: 10px;
    display: inline-block;
    font-size: 0.8em;
  }

  #company {
    float: right;
    text-align: right;
  }

  #project div,
  #company div {
    white-space: nowrap;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    border-spacing: 0;
    margin-bottom: 20px;
  }

  table tr:nth-child(2n-1) td {
    background: #F5F5F5;
  }

  table th,
  table td {
    text-align: center;
  }

  table th {
    padding: 5px 20px;
    color: #5D6975;
    border-bottom: 1px solid #C1CED9;
    white-space: nowrap;
    font-weight: normal;
  }

  table .service,
  table .desc {
    text-align: left;
  }

  table td {
    padding: 20px;
    text-align: right;
  }

  table td.service,
  table td.desc {
    vertical-align: top;
  }

  table td.unit,
  table td.qty,
  table td.total {
    font-size: 1.2em;
  }

  table td.grand {
    border-top: 1px solid #5D6975;;
  }

  #notices .notice {
    color: #5D6975;
    font-size: 1.2em;
  }

  footer {
    color: #5D6975;
    width: 100%;
    height: 30px;
    position: absolute;
    bottom: 0;
    border-top: 1px solid #C1CED9;
    padding: 8px 0;
    text-align: center;
  }

    </style>
  </head>
  <body>
    <header class="clearfix">
      <h1>Booking confirmation</h1>
      <div id="company" class="clearfix">
        <div>Restaurant Grails</div>
        <div>3 Foggy Heights,<br /> AZ 850, US</div>
        <div>898-519-0450</div>
        <div><a href="mailto:company@example.com">restaurantGrails@example.com</a></div>
      </div>
      <div id="project">
        <div><span>USERNAME</span> ${placeBooking.user.username}</div>
        <div><span>FIRST NAME</span> ${placeBooking.user.firstName}</div>
        <div><span>LAST NAME</span> ${placeBooking.user.lastName}</div>
        <div><span>EMAIL</span> ${placeBooking.user.email}</div>
        <div><span>TELEPHONE</span> ${placeBooking.user.telephone}</div>
      </div>
    </header>
    <main>
      <table>
        <thead>
          <tr>
            <th class="service">TABLE NUMBER</th>
            <th class="desc">PRICE PER HOUR</th>
            <th>DATE</th>
            <th>HOUR START</th>
            <th>HOUR STOP</th>
            <th>TOTAL</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="qty">${placeBooking.place.tableNumber}</td>
            <td class="unit">${placeBooking.place.pricePerHour}</td>
            <td class="service">${date}</td>
            <td class="service">${placeBooking.hourStart}</td>
            <td class="service">${placeBooking.hours}</td>
            <td class="total">${total}</td>
          </tr>
        </tbody>
      </table>
    </main>
  </body>
</html>