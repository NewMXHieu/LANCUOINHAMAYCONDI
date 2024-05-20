document.addEventListener("DOMContentLoaded", function () {
    const tableRows = document.querySelectorAll("#dataTable tbody tr");
    const rowsPerPage = 10;
    let currentPage = 0;
    const totalPages = Math.ceil(tableRows.length / rowsPerPage);
    const upTbButton = document.getElementById('upTbButton');
    const upTbButtonContent = document.getElementById('upTbButtonContent');
    const checkboxes = document.querySelectorAll('#dataTable tbody tr td input[type="checkbox"]');
    const TbButton = document.getElementById('TbButton');
    const pageNumbersContainer = document.getElementById('pageNumbers');
    const maThietBiMuonInput = document.getElementById('maThietBiMuon');
    const closeFormButton = document.getElementById('closeFormButton');

    closeFormButton.addEventListener('click', function () {
        hideupTbButton();
    });
    function showPage(page) {
        const startIndex = page * rowsPerPage;
        const endIndex = Math.min(startIndex + rowsPerPage, tableRows.length);

        for (let i = 0; i < tableRows.length; i++) {
            if (i >= startIndex && i < endIndex) {
                tableRows[i].style.display = "table-row";
            } else {
                tableRows[i].style.display = "none";
            }
        }
    }

    function updatePageNumbers() {
        pageNumbersContainer.innerHTML = '';
        for (let i = 1; i <= totalPages; i++) {
            createPageNumberButton(i);
        }
    }

    function createPageNumberButton(pageNumber) {
        const pageNumberButton = document.createElement('button');
        pageNumberButton.textContent = pageNumber;
        pageNumberButton.addEventListener('click', function () {
            currentPage = pageNumber - 1;
            showPage(currentPage);
        });
        pageNumbersContainer.appendChild(pageNumberButton);
    }

    function updateTbButtonStatus() {
        let checkedCount = 0;
        checkboxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                checkedCount++;
            }
        });
        TbButton.disabled = checkedCount !== 1;
    }
    
    function showupTbButton() {
        upTbButton.style.display = 'block';
    }

    function hideupTbButton() {
        upTbButton.style.display = 'none';
    }

    function getSelectedThietBi() {
        const selectedThietBi = [];
        checkboxes.forEach(function (checkbox, index) {
            if (checkbox.checked) {
                const maThietBi = tableRows[index].querySelectorAll('td')[2].textContent;
                selectedThietBi.push(maThietBi);
            }
        });
        return selectedThietBi.join(', ');
    }

    function updateMaThietBiMuonInput() {
        maThietBiMuonInput.value = getSelectedThietBi();
    }

    TbButton.addEventListener('click', function () {
        if (TbButton.disabled === false) {
            showupTbButton();
            updateMaThietBiMuonInput();
        }
    });

    // Sự kiện để ẩn form khi click ra ngoài form
    window.addEventListener('click', function (event) {
        if (event.target === upTbButton) {
            hideupTbButton();
        }
    });

    showPage(currentPage);

    document.getElementById("nextPage").addEventListener("click", function () {
        if (currentPage < totalPages - 1) {
            currentPage++;
            showPage(currentPage);
        }
    });

    document.getElementById("prevPage").addEventListener("click", function () {
        if (currentPage > 0) {
            currentPage--;
            showPage(currentPage);
        }
    });

    const prevButton = document.getElementById('prevPage');
    const nextButton = document.getElementById('nextPage');

    updatePageNumbers();
    updateTbButtonStatus();

    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('click', function () {
            updateTbButtonStatus();
            updateMaThietBiMuonInput();
        });
    });

    prevButton.addEventListener('click', function () {
        if (currentPage > 0) {
            currentPage--;
            showPage(currentPage);
        }
    });

    nextButton.addEventListener('click', function () {
        if (currentPage < totalPages - 1) {
            currentPage++;
            showPage(currentPage);
        }
    });
});
document.getElementById('TbButton').addEventListener('click', function () {
    // Lấy ra checkbox đã chọn
    const selectedCheckbox = document.querySelector('#dataTable tbody tr input[type="checkbox"]:checked');
    if (selectedCheckbox) {
        // Lấy ra hàng chứa checkbox đã chọn
        const selectedRow = selectedCheckbox.closest('tr');
        
        // Lấy ra các ô dữ liệu trong hàng đã chọn
        const cells = selectedRow.querySelectorAll('td');

        // Lấy ra các giá trị từ các ô dữ liệu
        const maThanhVien = cells[2].textContent;
        const maThietBi = cells[3].textContent;
        const tgvao = cells[4].textContent;
        const tgmuon = cells[5].textContent;
        const tgtra = cells[6].textContent;
        const tgdatcho = cells[7].textContent;


        // Điền các giá trị vào các trường trong form chỉnh sửa
        document.getElementById('maThanhVien').value = maThanhVien;
        document.getElementById('maThietBi').value = maThietBi;
        document.getElementById('tgvao').value = tgvao;
        document.getElementById('tgmuon').value = tgmuon;
        document.getElementById('tgtra').value = tgtra;
        document.getElementById('tgdatcho').value = tgdatcho;

        // Hiển thị form chỉnh sửa
        document.getElementById('updateTvForm').style.display = 'block';
    } else {
        // Hiển thị thông báo nếu không có thành viên nào được chọn
        alert('Vui lòng chọn một thành viên để sửa.');
    }
});
document.addEventListener("DOMContentLoaded", function () {
    const upTbButton = document.getElementById("upTbButton");
    const closeFormButton = document.getElementById("closeFormButton");
    const TvButton2 = document.getElementById("TvButton2");

    // Function to show the form
    function showForm() {
        upTbButton.style.display = "block";
    }

    // Function to hide the form
    function hideForm() {
        upTbButton.style.display = "none";
    }

    // Event listener to show the form when clicking "Thêm Vào Khu Học Tập"
    TvButton2.addEventListener("click", function () {
        showForm();

        // Reset the form fields for adding a new member
        document.getElementById("maThanhVien").value = "";
        document.getElementById("maThietBi").value = "";
        document.getElementById("tgvao").value = "";
        document.getElementById("tgmuon").value = "";
        document.getElementById("tgtra").value = "";
        document.getElementById("tgdatcho").value = "";
    });

    // Event listener to hide the form when clicking the close button
    closeFormButton.addEventListener("click", function () {
        hideForm();
    });
});