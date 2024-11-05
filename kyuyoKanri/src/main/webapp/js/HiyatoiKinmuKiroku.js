/**
 * 
 */
 // 전체 사원을 선택하거나 선택 해제하는 함수 (全社員を選択または選択解除する関数)
 function selectAllShain(selectAll) {
        const checkboxes = document.querySelectorAll('.shainCheckbox');
        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
        });
    }