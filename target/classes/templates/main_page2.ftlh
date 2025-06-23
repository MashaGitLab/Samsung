<#import "parts/common.ftlh" as c>
<@c.page>
</@c.page>

  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Company Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/main_page.css">
  </head>
  <body>
  <!-- Бежевый фон -->
  <section class="bg-beige d-flex align-items-center vh-100">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <h5>Maria Enterprise</h5>
          <h2 class="tagline">Отследите каждого!</h2>
          <p class="description">Система регистрации доступа обеспечивает полный контроль над посещением аудитории, предоставляя Вам точную информацию о том, кто и когда заходил в помещение.</p>
          <button class="btn btn-primary btn-register" onclick="showBubbles(event)">
            Вход <span class="arrow">→</span>
          </button>
        </div>
        <div class="col-md-6 d-flex justify-content-end align-items-center">
          <img src="/static/PhotoForMain/lock1.jpg" alt="Large Image" class="img-fluid img-custom">
        </div>
      </div>
    </div>
  </section>

  <!-- Черный фон -->
  <section class="bg-dark d-flex align-items-center vh-100">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <div class="large-text" data-target="content2">Интеллектуальный контроль доступа: ESP32 - сердце умной аудитории</div>
          <div class="small-text" id="content2" style="display: none;">
            ESP32 - это мощный микроконтроллер, который обеспечивает интеллектуальное управление доступом в аудиторию. С его помощью реализуется обработка данных с NFC-меток, управление реле для открытия дверей, контроль светодиодной индикации и звуковых сигналов. ESP32 обеспечивает беспроводное подключение к системе управления, позволяя в реальном времени отслеживать и контролировать доступ в помещение.
          </div>
          <hr class="text-divider">

          <div class="large-text" data-target="content1">Бесконтактная идентификация: модуль PN532 для безопасного доступа</div>
          <div class="small-text" id="content1" style="display: none;">
            NFC-модуль PN532 обеспечивает бесконтактную идентификацию пользователей с помощью RFID-меток. Этот модуль позволяет считывать уникальные идентификаторы карт, передавать их на обработку в систему и обеспечивать безопасный доступ в аудиторию. PN532 поддерживает различные стандарты RFID и NFC, что делает его универсальным решением для систем контроля доступа.
          </div>
        </div>
        <div class="col-md-6 img-container">
          <img src="/static/PhotoForMain/pn532.webp" alt="Image 1" class="img-fluid img-custom-pic" id="image1" data-target="content1">
          <img src="/static/PhotoForMain/esp32.jpg" alt="Image 2" class="img-fluid img-custom-pic" id="image2" data-target="content2">
        </div>
      </div>
    </div>
  </section>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="/static/main_page.js"></script>
  </body>
  </html>

<footer style="display: flex; justify-content: center; align-items: flex-end; height: 100px;">
  <p>&copy; 2025 by Maria</p>
</footer>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"
></script>