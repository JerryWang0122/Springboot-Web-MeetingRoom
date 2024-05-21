document.addEventListener('DOMContentLoaded', function() {

    // 按兩下，可以切換小月曆
    document.querySelectorAll('.date-text').forEach(span => {
        span.addEventListener('dblclick', event => {
            // console.log(span)
            const bookingId = event.target.dataset.bookingId;
            const input = document.querySelector(`.date-input[data-booking-id='${bookingId}']`)
            // console.log(bookingId)
            span.style.display = 'none';
            input.style.display = 'inline';
        })
    })

    document.querySelectorAll('.date-input').forEach(input => {
        // 監聽日期是否有被改變
        input.addEventListener('change', event => {
            const bookingId = event.target.dataset.bookingId;
            const bookingDate = event.target.value;
            // console.log(bookingId, bookingDate);

            // 透過 fetch 來變更資料庫的資訊
            fetch('http://localhost:8082/booking/date')
        })

        // 按右鍵，可以還原文字呈現(隱藏小月曆)
        input.addEventListener('contextmenu', event => {
            event.preventDefault();
            const bookingId = event.target.dataset.bookingId;
            const span = document.querySelector(`.date-text[data-booking-id='${bookingId}']`)
            span.style.display = 'inline';
            input.style.display = 'none';
        })
    })
});