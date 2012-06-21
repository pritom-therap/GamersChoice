<%--
  Created by IntelliJ IDEA.
  User: pritom
  Date: 6/6/12
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jmesa" prefix="jmesa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Simple jsp page</title>

</head>
<body>
<div class="post">
    <script type="text/javascript" >

        /*********** Convenience API Methods ***********/

        function addTableFacadeToManager(id) {
            jQuery.jmesa.addTableFacade(id);
        }

        function setSaveToWorksheet(id) {
            jQuery.jmesa.setSaveToWorksheet(id);
        }

        function setFilterToWorksheet(id) {
            jQuery.jmesa.setFilterToWorksheet(id);
        }

        function removeFilterFromWorksheet(id) {
            jQuery.jmesa.removeFilterFromWorksheet(id);
        }

        function setPageToLimit(id, page) {
            jQuery.jmesa.setPageToLimit(id, page);
        }

        function setMaxRowsToLimit(id, maxRows) {
            jQuery.jmesa.setMaxRowsToLimit(id, maxRows);
        }

        function addSortToLimit(id, position, property, order) {
            jQuery.jmesa.addSortToLimit(id, position, property, order);
        }

        function removeSortFromLimit(id, property) {
            jQuery.jmesa.removeSortFromLimit(id, property);
        }

        function removeAllSortsFromLimit(id) {
            jQuery.jmesa.removeAllSortsFromLimit(id);
        }

        function getSortFromLimit(id, property) {
            jQuery.jmesa.getSortFromLimit(id, property);
        }

        function addFilterToLimit(id, property) {
            jQuery.jmesa.addFilterToLimit(id, property);
        }

        function removeFilterFromLimit(id, property) {
            jQuery.jmesa.removeFilterFromLimit(id, property);
        }

        function removeAllFiltersFromLimit(id) {
            jQuery.jmesa.removeAllFiltersFromLimit(id);
        }

        function getFilterFromLimit(id, property) {
            jQuery.jmesa.getFilterFromLimit(id, property);
        }

        function setExportToLimit(id, exportType) {
            jQuery.jmesa.setExportToLimit(id, exportType);
        }

        function createHiddenInputFieldsForLimit(id) {
            jQuery.jmesa.createHiddenInputFieldsForLimit(id);
        }

        function createHiddenInputFieldsForLimitAndSubmit(id) {
            jQuery.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
        }

        function createParameterStringForLimit(id) {
            return jQuery.jmesa.createParameterStringForLimit(id);
        }

        /*********** Filter ***********/

        function createDynFilter(filter, id, property) {
            jQuery.jmesa.createDynFilter(filter, id, property);
        }

        function createDynDroplistFilter(filter, id, property, options) {
            jQuery.jmesa.createDroplistDynFilter(filter, id, property, options);
        }

        /*********** Worksheet ***********/

        function createWsColumn(column, id, uniqueProperties, property) {
            jQuery.jmesa.createWsColumn(column, id, uniqueProperties, property);
        }

        function submitWsCheckboxColumn(column, id, uniqueProperties, property) {
            jQuery.jmesa.submitWsCheckboxColumn(column, id, uniqueProperties, property);
        }

        function submitWsColumn(originalValue, changedValue) {
            jQuery.jmesa.submitWsColumn(originalValue, changedValue);
        }

        /*********** Special Effects ***********/

        function addDropShadow(imagesPath, theme) {
            jQuery.jmesa.addDropShadow(imagesPath, theme);
        }

    </script>
    <script type="text/javascript">
    (function ($) {

        var tableFacades = new Object();

        var getFormByTableId = function(id) {
            var node = document.getElementById(id);
            var found = false;
            while (!found) {
                if (node.nodeName == 'FORM') {
                    found = true;
                    return node;
                }
                node = node.parentNode;
            }
            return null;
        }

        var coreapi = {
            addTableFacade : function(id) {
                var tableFacade = new classes.TableFacade(id);
                tableFacades[tableFacade.limit.id] = tableFacade;
            },
            getTableFacade : function(id) {
                return tableFacades[id];
            },
            setSaveToWorksheet : function(id) {
                this.getTableFacade(id).worksheet.save='true';
            },
            setFilterToWorksheet : function(id) {
                this.getTableFacade(id).worksheet.filter='true';
                this.setPageToLimit(id, '1');
            },
            setClearToWorksheet : function(id) {
                this.getTableFacade(id).worksheet.clear='true';
                this.removeFilterFromWorksheet(id);
            },
            setAddRowToWorksheet : function(id) {
                this.getTableFacade(id).worksheet.addRow='true';
            },
            setRemoveRowToWorksheet : function(id, uniqueProperties) {
                this.getTableFacade(id).worksheet.removeRow = uniqueProperties;
            },
            removeFilterFromWorksheet : function(id) {
                this.getTableFacade(id).worksheet.filter=null;
                this.setPageToLimit(id, '1');
            },
            setPageToLimit : function(id, page) {
                this.getTableFacade(id).limit.setPage(page);
            },
            setMaxRowsToLimit : function(id, maxRows) {
                this.getTableFacade(id).limit.setMaxRows(maxRows);
                this.setPageToLimit(id, '1');
            },
            setTotalRowsToLimit : function(id, totalRows) {
                this.getTableFacade(id).limit.setTotalRows(totalRows);
            },
            addSortToLimit : function(id, position, property, order) {
                /*First remove the sort if it is set on the limit,
                   and then set the page back to the first one.*/
                this.removeSortFromLimit(id, property);
                this.setPageToLimit(id, '1');

                var limit = this.getTableFacade(id).limit;
                var sort = new classes.Sort(position, property, order);
                limit.addSort(sort);
            },
            removeSortFromLimit : function(id, property) {
                var limit = this.getTableFacade(id).limit;
                var sortSet = limit.getSortSet();
                $.each(sortSet, function(index, sort) {
                    if (sort.property == property) {
                        sortSet.splice(index, 1);
                        return false;
                    }
                });
            },
            removeAllSortsFromLimit : function(id) {
                var limit = this.getTableFacade(id).limit;
                limit.setSortSet(new Array());
                this.setPageToLimit(id, '1');
            },
            getSortFromLimit : function(id, property) {
                var limit = this.getTableFacade(id).limit;
                var sortSet = limit.getSortSet();
                $.each(sortSet, function(index, sort) {
                    if (sort.property == property) {
                        return sort;
                    }
                });
            },
            addFilterToLimit : function(id, property, value) {
                /*First remove the filter if it is set on the limit,
                  and then set the page back to the first one.*/
                this.removeFilterFromLimit(id, property);
                this.setPageToLimit(id, '1');

                var limit = this.getTableFacade(id).limit;
                var filter = new classes.Filter(property, value);
                limit.addFilter(filter);
            },
            removeFilterFromLimit : function(id, property) {
                var limit = this.getTableFacade(id).limit;
                var filterSet = limit.getFilterSet();
                $.each(filterSet, function(index, filter) {
                    if (filter.property == property) {
                        filterSet.splice(index, 1);
                        return false;
                    }
                });
            },
            removeAllFiltersFromLimit : function(id) {
                var tableFacade = this.getTableFacade(id);

                var limit = tableFacade.limit;
                limit.setFilterSet(new Array());
                this.setPageToLimit(id, '1');

                var worksheet = tableFacade.worksheet;
                worksheet.filter = null;
            },
            getFilterFromLimit : function(id, property) {
                var limit = this.getTableFacade(id).limit;
                var filterSet = limit.getFilterSet();
                $.each(filterSet, function(index, filter) {
                    if (filter.property == property) {
                        return filter;
                    }
                });
            },
            setExportToLimit : function(id, exportType) {
                this.getTableFacade(id).limit.setExport(exportType);
            },
            createHiddenInputFieldsForLimit : function(id) {
                var tableFacade = this.getTableFacade(id);
                var form = getFormByTableId(id);
                tableFacade.createHiddenInputFields(form);
            },
            createHiddenInputFieldsForLimitAndSubmit : function(id) {
                var tableFacade = this.getTableFacade(id);
                var form = getFormByTableId(id);
                var created = tableFacade.createHiddenInputFields(form);
                if (created) {
                    form.submit();
                }
            },
            createHiddenInputFieldsAllTablesForLimitAndSubmit : function() {
                var form;
                var created;

                $.each(tableFacades, function(id, tableFacade) {
                    if (!form) {
                        form = getFormByTableId(id);
                    }
                    created = tableFacade.createHiddenInputFields(form);
                });

                if (created) {
                    form.submit();
                }
            },
            createParameterStringForLimit : function(id) {
                var tableFacade = this.getTableFacade(id);
                return tableFacade.createParameterString();
            },
            setOnInvokeAction : function (id, functionName) {
                var tableFacade = this.getTableFacade(id);
                tableFacade.onInvokeAction = functionName;
            },
            setOnInvokeExportAction : function (id, functionName) {
                var tableFacade = this.getTableFacade(id);
                tableFacade.onInvokeExportAction = functionName;
            },
            setContextPath : function (id, contextPath) {
                var tableFacade = this.getTableFacade(id);
                tableFacade.contextPath = contextPath;
            },
            getContextPath : function (id) {
                var tableFacade = this.getTableFacade(id);
                return tableFacade.contextPath;
            },
            onInvokeAction : function (id, action) {
                var tableFacade = this.getTableFacade(id);
                var f = window[tableFacade.onInvokeAction];
                if ($.isFunction(f) !== true) {
                    throw tableFacade.onInvokeAction + ' is not a global function!';
                } else {
                    f(id, action);
                }
            },
            onInvokeExportAction : function (id) {
                var tableFacade = this.getTableFacade(id);
                var f = window[tableFacade.onInvokeExportAction];
                if ($.isFunction(f) !== true) {
                    throw tableFacade.onInvokeExportAction + ' is not a global function!';
                } else {
                    f(id);
                }
            },
            getFormByTableId : function (id) {
                return getFormByTableId(id);
            }
        };

        /*********** Objects and Prototype Functions ***********/

        var classes = {
            TableFacade : function (id) {
                this.limit = new classes.Limit(id);
                this.worksheet = new classes.Worksheet();
                this.onInvokeAction = 'onInvokeAction';
                this.onInvokeExportAction = 'onInvokeExportAction';
                this.contextPath = '';
            },
            Worksheet : function () {
                this.save = null;
                this.filter = null;
                this.clear = null;
                this.addRow = null;
                this.removeRow = null;
            },
            Limit : function (id) {
                this.id = id;
                this.page = null;
                this.maxRows = null;
                this.totalRows = null;
                this.sortSet = [];
                this.filterSet = [];
                this.exportType = null;
            },
            Sort : function (position, property, order) {
                this.position = position;
                this.property = property;
                this.order = order;
            },
            Filter : function (property, value) {
                this.property = property;
                this.value = value;
            },
            DynFilter : function (filter, id, property) {
                this.filter = filter;
                this.id = id;
                this.property = property;
            },
            WsColumn : function (column, id, uniqueProperties, property) {
                this.column = column;
                this.id = id;
                this.uniqueProperties = uniqueProperties;
                this.property = property;
            }
        };

        $.extend(classes.Limit.prototype, {
            getId : function () {
                return this.id;
            },
            setId : function (id) {
                this.id = id;
            },
            getPage : function () {
                return this.page;
            },
            setPage : function (page) {
                this.page = page;
            },
            getMaxRows : function () {
                return this.maxRows;
            },
            setMaxRows : function (maxRows) {
                this.maxRows = maxRows;
            },
            getTotalRows : function () {
                return this.totalRows;
            },
            setTotalRows : function (totalRows) {
                this.totalRows = totalRows;
            },
            getTotalPages : function () {
                if (this.maxRows == 0) return 1;
                var pages = this.totalRows / this.maxRows;
                if ((this.totalRows % this.maxRows) > 0) {
                    ++pages;
                }
                return pages;
            },
            getSortSet : function () {
                return this.sortSet;
            },
            addSort : function (sort) {
                this.sortSet[this.sortSet.length] = sort;
            },
            setSortSet : function (sortSet) {
                this.sortSet = sortSet;
            },
            getFilterSet : function () {
                return this.filterSet;
            },
            addFilter : function (filter) {
                this.filterSet[this.filterSet.length] = filter;
            },
            setFilterSet : function (filterSet) {
                this.filterSet = filterSet;
            },
            getExport : function () {
                return this.exportType;
            },
            setExport : function (exportType) {
                this.exportType = exportType;
            }
        });

        $.extend(classes.TableFacade.prototype, {
            createHiddenInputFields : function(form) {
                var limit = this.limit;

                var exists = $(form).find(':hidden[name=' + limit.id + '_p_]').val();
                if (exists) {
                    return false;
                }

                if (this.worksheet.save) {
                    $(form).append('<input type="hidden" name="' + limit.id + '_sw_" value="true"/>');
                }

                if (this.worksheet.filter) {
                    $(form).append('<input type="hidden" name="' + limit.id + '_fw_" value="true"/>');
                }

                if (this.worksheet.clear) {
                    $(form).append('<input type="hidden" name="' + limit.id + '_cw_" value="true"/>');
                }

                if (this.worksheet.addRow) {
                    $(form).append('<input type="hidden" name="' + limit.id + '_awr_" value="true"/>');
                }

                if (this.worksheet.removeRow) {
                    $.each(this.worksheet.removeRow, function(key, value) {
                        $(form).append('<input type="hidden" name="' + limit.id + '_rwr_" value="' + value + '"/>');
                    });
                }

                /* tip the API off that in the loop of working with the table */
                $(form).append('<input type="hidden" name="' + limit.id + '_tr_" value="true"/>');

                /* the current page */
                $(form).append('<input type="hidden" name="' + limit.id + '_p_" value="' + limit.page + '"/>');
                $(form).append('<input type="hidden" name="' + limit.id + '_mr_" value="' + limit.maxRows + '"/>');

                /* the sort objects */
                var sortSet = limit.getSortSet();
                $.each(sortSet, function(index, sort) {
                    $(form).append('<input type="hidden" name="' + limit.id + '_s_'  + sort.position + '_' + sort.property + '" value="' + sort.order + '"/>');
                });

                /* the filter objects */
                var filterSet = limit.getFilterSet();
                $.each(filterSet, function(index, filter) {
                    $(form).append('<input type="hidden" name="' + limit.id + '_f_' + filter.property + '" value="' + filter.value + '"/>');
                });

                return true;
            },
            createParameterString : function() {
                var limit = this.limit;

                var url = '';

                /* the current page */
                url += limit.id + '_p_=' + limit.page;

                /* the max rows */
                url += '&' + limit.id + '_mr_=' + limit.maxRows;

                /* the sort objects */
                var sortSet = limit.getSortSet();
                $.each(sortSet, function(index, sort) {
                    url += '&' + limit.id + '_s_' + sort.position + '_' + sort.property + '=' + sort.order;
                });

                /* the filter objects */
                var filterSet = limit.getFilterSet();
                $.each(filterSet, function(index, filter) {
                    url += '&' + limit.id + '_f_' + filter.property + '=' + encodeURIComponent(filter.value);
                });

                /* the export */
                if (limit.exportType) {
                    url += '&' + limit.id + '_e_=' + limit.exportType;
                }

                /* tip the API off that in the loop of working with the table */
                url += '&' + limit.id + '_tr_=true';

                if (this.worksheet.save) {
                    url += '&' + limit.id + '_sw_=true';
                }

                if (this.worksheet.filter) {
                    url += '&' + limit.id + '_fw_=true';
                }

                if (this.worksheet.clear) {
                    url += '&' + limit.id + '_cw_=true';
                }

                if (this.worksheet.addRow) {
                    url += '&' + limit.id + '_awr_=true';
                }

                if (this.worksheet.removeRow) {
                    $.each(this.worksheet.removeRow, function(key, value) {
                        url += '&' + limit.id + '_rwr_=' + value;
                    });
                }

                return url;
            }
        });

        /************* Filter ***********/

        var dynFilter = null;

        var filterapi = {
            createDynFilter : function(filter, id, property) {
                if (dynFilter) {
                    return;
                }

                dynFilter = new classes.DynFilter(filter, id, property);

                var cell = $(filter);
                var width = cell.width();
                var originalValue = cell.text();

                /* Enforce the width with a style. */
                cell.width(width);
                cell.parent().width(width);
                cell.css('overflow', 'visible');

                cell.html('<div id="dynFilterDiv"><input id="dynFilterInput" name="filter" style="width:' + (width + 2) + 'px" value="" /></div>');

                var input = $('#dynFilterInput');
                input.val(originalValue);
                input.focus();
                if (jQuery.browser.msie) { /* IE need a second focus */
                    input.focus();
                }

                $(input).keydown(function(event) {
                    var id = dynFilter.id;
                    if (event.keyCode == 13) { /* Press the enter key. */
                        $.jmesa.saveDynFilterData(cell, input, originalValue);
                        $.jmesa.onInvokeAction(id, 'filter');
                    } else if (event.keyCode == 9) { /* Press the tab key. */
                        var divToClick = $.jmesa.findNextCell(id, 'dynFilter', event.shiftKey);
                        $.jmesa.saveDynFilterData(cell, input, originalValue);
                        if (divToClick != null){
                            divToClick.onclick();
                            return false; /* Stop event for IE */
                        }
                    }
                });

                $(input).blur(function() {
                    $.jmesa.saveDynFilterData(cell, input, originalValue);
                });
            },
            saveDynFilterData : function(cell, input, originalValue) {
                var changedValue = input.val();
                cell.text('');
                cell.css('overflow', 'hidden');
                cell.text(changedValue);
                if(originalValue != changedValue) {
                   $.jmesa.addFilterToLimit(dynFilter.id, dynFilter.property, changedValue);
                }
                dynFilter = null;
            },
            createDroplist : function(droplistDivId, droplistInputId, cell, options) {
                if ($('#' + droplistDivId).size() > 0) {
                    return false; /* Droplist already created. */
                }

                cell.css('overflow', 'visible');

                /* Get the original cell value and width. */
                var originalValue = cell.text();
                var width = cell.width();

                /* Need to first get the size of the array. */
                var size = 0;
                $.each(options, function() {
                    size++;
                    if (size > 10) {
                        size = 10;
                        return false;
                    }
                });

                /* Enforce the width with a style. */
                cell.width(width);
                cell.parent().width(width);

                /* Create the dynamic select input box. */
                cell.html('<div id="' + droplistDivId + '" style="top:17px">');

                var html = '<select id="' + droplistInputId + '" size="' + size + '">';
                $.each(options, function(key, value) {
                    if (key == originalValue) {
                        html += '<option selected="selected" value="' + key + '">' + value + '</option>';
                    } else {
                        html += '<option value="' + key + '">' + value + '</option>';
                    }
                });

                html += '</select>';

                var div = $('#' + droplistDivId);
                div.html(html);

                var input = $('#' + droplistInputId);

                /* Resize the column if it is not at least as wide as the column. */
                var selectWidth = input.width();
                if (selectWidth < width) {
                    input.width(width + 5); /* always make the droplist overlap some */
                }

                input.focus();

                var originalBackgroundColor = cell.css("backgroundColor");
                cell.css({backgroundColor:div.css("backgroundColor")});

                return true;
            },
            createDroplistDynFilter : function(filter, id, property, options) {
                if (dynFilter) {
                    return;
                }

                dynFilter = new classes.DynFilter(filter, id, property);

                /* The cell that represents the filter. */
                var cell = $(filter);
                var originalValue = cell.text();
                var originalBackgroundColor = cell.css("backgroundColor");

                var droplistDivId = 'dynFilterDroplistDiv';
                var droplistInputId = 'dynFilterDroplist';

                if (!($.jmesa.createDroplist(droplistDivId, droplistInputId, cell, options))) {
                   return;
                }

                var div = $('#' + droplistDivId);
                var input = $('#' + droplistInputId);

                /* Something was selected or the clicked off the droplist. */

                $(input).click(function() {
                    var id = dynFilter.id;
                    $.jmesa.saveDroplistDynFilterData(cell, originalValue, originalBackgroundColor);
                    $.jmesa.onInvokeAction(id, 'filter');
                });

                $(input).blur(function() {
                    $.jmesa.saveDroplistDynFilterData(cell, originalValue, originalBackgroundColor);
                });

                div.keydown(function(event) {
                    var id = dynFilter.id;
                    if (event.keyCode == 13) { /* Press the enter key. */
                        $.jmesa.saveDroplistDynFilterData(cell, originalValue, originalBackgroundColor);
                        $.jmesa.onInvokeAction(id, 'filter');
                    } else if (event.keyCode == 9) { /* Press the tab key. */
                        var divToClick = $.jmesa.findNextCell(id, 'dynFilter', event.shiftKey);
                        $.jmesa.saveDroplistDynFilterData(cell, originalValue, originalBackgroundColor);

                        if (divToClick != null){
                            divToClick.onclick();
                            return false; /* Stop event for IE */
                        }
                    }

                });
            },
            saveDroplistDynFilterData : function(cell, originalValue, originalBackgroundColor) {
                var changedValue = $("#dynFilterDroplistDiv option:selected").val();
                var changedText = $("#dynFilterDroplistDiv option:selected").text();
                cell.text(changedText);
                cell.css('overflow', 'hidden');
                if (originalValue != changedValue) {
                    $.jmesa.addFilterToLimit(dynFilter.id, dynFilter.property, changedValue);
                }
                $('#dynFilterDroplistDiv').remove();
                cell.css({backgroundColor:originalBackgroundColor});
                dynFilter = null;
            }
        }

        /*********** Worksheet ***********/

        var wsColumn = null;
        var errorMap;
        var validatorOptions = {};

        /* used when multiple tables are present in a single page */
        var previousTableId;

        var worksheetapi = {
            createWsColumn : function(column, id, uniqueProperties, property) {
                if (wsColumn) {
                    return;
                }

                wsColumn = new classes.WsColumn(column, id, uniqueProperties, property);

                var cell = $(column);
                var width = cell.width();
                var originalValue = cell.text();

                /* Enforce the width with a style. */
                cell.width(width);
                cell.parent().width(width);
                cell.css('overflow', 'visible');

                cell.html('<div id="wsColumnDiv"><input id="wsColumnInput" name="' + property + '" style="width:' + (width + 3) + 'px" value=""/></div>');

                var input = $('#wsColumnInput');
                input.val(originalValue);
                input.focus();
                if (jQuery.browser.msie) { /* IE need a second focus */
                    input.focus();
                }

                this.wsColumnKeyEvent(cell, input, originalValue);

                $('#wsColumnInput').blur(function() {
                    $.jmesa.validateAndSubmitWsColumn(cell, input, originalValue);
                });
            },
            createWsAutoCompleteColumn : function(column, id, uniqueProperties, property, url, options) {
                if (wsColumn) {
                    return;
                }

                wsColumn = new classes.WsColumn(column, id, uniqueProperties, property);

                var cell = $(column);
                var width = cell.width();
                var originalValue = cell.text();

                /* Enforce the width with a style. */
                cell.width(width);
                cell.parent().width(width);
                cell.css('overflow', 'visible');

                cell.html('<div id="wsColumnDiv"><input id="wsColumnInput" name="' + property + '" style="width:' + (width + 3) + 'px" value=""/></div>');

                $('input[name=' + property + ']').autocomplete(url, options);

                var input = $('#wsColumnInput');
                input.val(originalValue);
                input.focus();
                if (jQuery.browser.msie) { /* IE need a second focus */
                    input.focus();
                }

                this.wsColumnKeyEvent(cell, input, originalValue);

                $('#wsColumnInput').blur(function() {
                    $.jmesa.validateAndSubmitWsColumn(cell, input, originalValue);
                });
            },
            findNextCell : function(tableId, classToMatch, shiftKey) {
                var divToClick = null;
                var nextCell = false;
                var lastCell = false;
                var lastDiv = null;
                var firstDiv = null;

                /* identify wsColumn, wsColumnChange or wsColumnError if classToMatch startsWith wsColumn */
                $('#' + tableId).find('div:[class^=' + classToMatch + ']').each(function(index, divElement) {
                     if (firstDiv == null){
                         firstDiv = divElement;
                     }

                     if (nextCell){
                         divToClick = divElement;
                         return false;
                     } else if (divElement.style.overflow == 'visible'){
                         if (shiftKey){ /* shift-tabulation ==> Precedent cell */
                             if (divElement == firstDiv){ /* shitf-tabulation in first cell */
                                 lastCell = true;
                             } else {
                                 divToClick = lastDiv;
                                 return false;
                             }
                         } else { /* tabulation ==> Next cell */
                             nextCell = true;
                         }
                     }
                     lastDiv = divElement;
                });

                if (divToClick == null){
                    if (nextCell){ /* tabulation in last cell */
                        divToClick = firstDiv;
                    } else if (lastCell) { /* shitf-tabulation in first cell */
                        divToClick = lastDiv;
                    }
                }

                return divToClick;
            },
            wsColumnKeyEvent : function(cell, input, originalValue) {
                var keyEvent = function(event) {
                    if (event.keyCode == 13 || event.keyCode == 9) { /* Press the enter or tabulation key. */
                        var divToClick = null;

                        if (event.keyCode == 9) { /* Press the tabulation key ==> search last or next cell */
                            divToClick = $.jmesa.findNextCell(wsColumn.id, 'wsColumn', event.shiftKey);
                        }

                        $.jmesa.validateAndSubmitWsColumn(cell, input, originalValue);

                        if (divToClick != null){
                            divToClick.onclick();
                            return false; /* Stop event for IE */
                        }
                    } else if (event.shiftKey && event.ctrlKey && event.keyCode == 90) { /* Ctrl+Shift+z key to get original value (undo). */
                        /* Get original value */
                        if (cell.attr('class') != 'wsColumn') {
                           input.val(cell.attr('data-ov'));
                        }
                    }
                };

                /* Use keydown across all browsers as IE and Safari don't catch tabulation and Firefox doesn't catch Ctrl / Alt on keypress */
                input.keydown(keyEvent);
            },
            createWsDroplistColumn : function(column, id, uniqueProperties, property, options) {
                if (wsColumn) {
                    return;
                }

                wsColumn = new classes.WsColumn(column, id, uniqueProperties, property);

                /* The cell that represents the column. */
                var cell = $(column);
                var originalValue = cell.text();
                var originalBackgroundColor = cell.css("backgroundColor");

                var droplistDivId = 'wsColumnDroplistDiv';
                var droplistInputId = 'wsColumnDroplistInput';

                if (!($.jmesa.createDroplist(droplistDivId, droplistInputId, cell, options))) {
                   return;
                }

                var div = $('#' + droplistDivId);
                var input = $('#' + droplistInputId);

                /* Something was selected or the clicked off the droplist. */

                $(input).click(function() {
                    $.jmesa.submitWsDroplistColumn(cell, originalValue, originalBackgroundColor);
                    /* This little hack is required to remove the row highlighing */
                    cell.parent().parent().mouseout();
                    return false; /* Stop event for IE */
                });

                $(input).blur(function() {
                    $.jmesa.submitWsDroplistColumn(cell, originalValue, originalBackgroundColor);
                });

                div.keydown(function(event) {
                    var id = wsColumn.id;
                    if (event.keyCode == 13 || event.keyCode == 9) { /* Press the enter or tabulation key. */
                        if (event.keyCode == 13) { /* Press the enter key. */
                            $.jmesa.submitWsDroplistColumn(cell, originalValue, originalBackgroundColor);
                            /* This little hack is required to remove the row highlighing */
                            cell.parent().parent().mouseout();
                        } else if (event.keyCode == 9) { /* Press the tab key. */
                            var divToClick = $.jmesa.findNextCell(id, 'wsColumn', event.shiftKey);
                            $.jmesa.submitWsDroplistColumn(cell, originalValue, originalBackgroundColor);
                            /* This little hack is required to remove the row highlighing */
                            cell.parent().parent().mouseout();

                            if (divToClick != null){
                                divToClick.onclick();
                                return false; /* Stop event for IE */
                            }
                        }
                    } else if (event.shiftKey && event.ctrlKey && event.keyCode == 90) { /* Ctrl+Shift+z key to get original value (undo). */
                        /* Get original value */
                        if (cell.attr('class') != 'wsColumn') {
                           input.val(cell.attr('data-ov'));
                        }
                    }

                });
            },
            submitWsDroplistColumn : function(cell, originalValue, originalBackgroundColor) {
                var changedKey = $("#wsColumnDroplistDiv option:selected").val();
                var changedValue = $("#wsColumnDroplistDiv option:selected").text();
                cell.text(changedValue);
                cell.css('overflow', 'hidden');
                if (originalValue != changedValue) {
                    if (cell.attr('class') == 'wsColumn') {
                       /* use custom attribute to store original value */
                       cell.attr('data-ov', originalValue);
                    }
                    $.jmesa.submitWsColumn(originalValue, changedValue);
                }
                $('#wsColumnDroplistDiv').remove();
                cell.css({backgroundColor:originalBackgroundColor});
                wsColumn = null;
            },
            submitWsCheckboxColumn : function(column, id, uniqueProperties, property) {
                wsColumn = new classes.WsColumn(column, id, uniqueProperties, property);

                var checked = column.checked;

                var changedValue = 'unchecked';
                if (checked) {
                    changedValue = 'checked';
                }

                var originalValue = 'unchecked';
                if (!checked) { /* The first time the original value is the opposite. */
                    originalValue = 'checked';
                }

                $.jmesa.submitWsColumn(originalValue, changedValue);

                wsColumn = null;
            },
            setError : function(em) {
               errorMap = em;
            },
            setValidator : function(id, options) {
               validatorOptions[id] = options;
            },
            getValidator : function(id) {
               if (!validatorOptions[id])
                   return;

               var form = $.jmesa.getFormByTableId(id);

               /* If there are multiple tables in a SINGLE form then... */
               /* 1. Check from which table the cell is getting modified */
               /* 2. If it is from same table that of last cell, do nothing */
               /* 3. If it is from different table that of last cell, check if the two tables are in single form */
                   /* 3.1 If yes, we need to delete the existing validator and create a new for the new table */
               /* This is a small hack (maipulation with data structure of validator plugin) */
               /* This is required since validator object gets created only ONCE per form */
               /* i.e, if a "validator" has been created (for previous table options), it will not be created again (for new table options) */
               if (previousTableId && (previousTableId != id) && ($.jmesa.getFormByTableId(previousTableId) == form)) {
                   $.data(form, 'validator', null);
               }

               previousTableId = id;
               return $(form).validate(validatorOptions[id]);
            },
            validateAndSubmitWsColumn : function(cell, input, originalValue) {
                var changedValue = input.val();
                var validator = $.jmesa.getValidator(wsColumn.id);
                var hasRules;
                if (validator) {
                    $.each(input.rules(), function() { hasRules = true; });
                }
                if (changedValue != originalValue) {
                    if (hasRules) {
                        /* trigger validation */
                        validator.element($('#wsColumnInput'));
                    }
                }
                cell.text('');
                cell.css('overflow', 'hidden');
                cell.text(changedValue);
                if (changedValue != originalValue) {
                    if (cell.attr('class') == 'wsColumn') {
                       /* use custom attribute to store original value */
                       cell.attr('data-ov', originalValue);
                    }
                    $.jmesa.submitWsColumn(originalValue, changedValue, hasRules);
                }
                wsColumn = null;
            },
            submitWsColumn : function(originalValue, changedValue, hasRules) {
                /* hasRules is optional parameter and will be true only if the column has validations */
                var data = '{ "id" : "' + wsColumn.id + '"';

                data += ', "cp_" : "' + wsColumn.property + '"';

                var props = wsColumn.uniqueProperties;
                $.each(props, function(key, value) {
                    data += ', "up_' + key + '" : "' + value + '"';
                });

                data += ', "ov_" : "' + encodeURIComponent(originalValue) + '"';
                data += ', "cv_" : "' + encodeURIComponent(changedValue) + '"';

                var cell = $(wsColumn.column);
                var errorMessage;

                if (hasRules) {
                   if (errorMap) {
                      errorMessage = errorMap[wsColumn.property];
                   }
                } else {
                   errorMessage = cell.attr('data-em');
                }

                if (errorMessage) {
                   data += ', "em_" : "' + encodeURIComponent(errorMessage) + '"';
                }

                data += '}'

                var contextPath = coreapi.getContextPath(wsColumn.id);
                if (contextPath) {
                   contextPath += "/";
                }

                $.post(contextPath + 'jmesa.wrk', jQuery.parseJSON(data), function(columnStatus) {
                   jQuery.jmesa.updateCssClass(columnStatus, cell, errorMessage);
                });
            },
            updateCssClass : function(columnStatus, cell, errorMessage) {
                /* Column status returned from worksheet servlet */
                var classNames = {
                   "_rm_" : 'wsColumn',
                   "_uu_" : 'wsColumnChange',
                   "_ue_" : 'wsColumnError'
                };

                /* Skip other editors, e.g. checkbox etc */
                if (cell.attr('class').indexOf('wsColumn') == 0) {
                     cell.attr('class', classNames[columnStatus]);

                     if ('wsColumn' == classNames[columnStatus]) {
                        cell.removeAttr('data-ov');
                     }

                     if (errorMessage) {
                        /* use custom attribute to store error message */
                        cell.attr('data-em', errorMessage);
                     } else {
                        cell.removeAttr('data-em');
                     }
                }
            },
            setTitle : function(column, e) {
                var title;
                var cell = $(column);

                if(e.ctrlKey) {
                    title = cell.attr("data-ov");
                } else {
                    title = cell.attr("data-em");
                }

                if(title) {
                    cell.attr("title", title);
                } else {
                    cell.removeAttr("title");
                }
            }
        }

        /************* Special Effects ***********/

        var effectsapi = {
            addDropShadow : function(imagesPath, theme) {
                if (!theme) {
                    theme = 'jmesa';
                }
                $('div.' + theme + ' .table')
                .wrap("<div class='wrap0'><div class='wrap1'><div class='wrap2'><div class='dropShadow'></div></div></div></div>")
                .css({'background': 'url(' + imagesPath + 'shadow_back.gif) 100% repeat'});

                $('.' + theme + ' div.wrap0').css({'background': 'url(' + imagesPath + 'shadow.gif) right bottom no-repeat', 'float':'left'});
                $('.' + theme + ' div.wrap1').css({'background': 'url(' + imagesPath + 'shadow180.gif) no-repeat'});
                $('.' + theme + ' div.wrap2').css({'background': 'url(' + imagesPath + 'corner_bl.gif) -18px 100% no-repeat'});
                $('.' + theme + ' div.dropShadow').css({'background': 'url(' + imagesPath + 'corner_tr.gif) 100% -18px no-repeat'});

                $('div.' + theme).append('<div style="clear:both">&nbsp;</div>');
            }
        }

        /* Put all the methods under the $.jmesa context. */

        $.extend(coreapi, filterapi);
        $.extend(coreapi, worksheetapi);
        $.extend(coreapi, effectsapi);
        $.jmesa = {};
        $.extend($.jmesa, coreapi);

    })(jQuery);

    </script>
    <script type="text/javascript" >
        (function(e){var j=new Object();var i=function(p){var n=document.getElementById(p);var o=false;while(!o){if(n.nodeName=="FORM"){o=true;return n}n=n.parentNode}return null};var a={addTableFacade:function(o){var n=new c.TableFacade(o);j[n.limit.id]=n},getTableFacade:function(n){return j[n]},setSaveToWorksheet:function(n){this.getTableFacade(n).worksheet.save="true"},setFilterToWorksheet:function(n){this.getTableFacade(n).worksheet.filter="true";this.setPageToLimit(n,"1")},setClearToWorksheet:function(n){this.getTableFacade(n).worksheet.clear="true";this.removeFilterFromWorksheet(n)},setAddRowToWorksheet:function(n){this.getTableFacade(n).worksheet.addRow="true"},setRemoveRowToWorksheet:function(o,n){this.getTableFacade(o).worksheet.removeRow=n},removeFilterFromWorksheet:function(n){this.getTableFacade(n).worksheet.filter=null;this.setPageToLimit(n,"1")},setPageToLimit:function(o,n){this.getTableFacade(o).limit.setPage(n)},setMaxRowsToLimit:function(o,n){this.getTableFacade(o).limit.setMaxRows(n);this.setPageToLimit(o,"1")},setTotalRowsToLimit:function(o,n){this.getTableFacade(o).limit.setTotalRows(n)},addSortToLimit:function(s,o,r,n){this.removeSortFromLimit(s,r);this.setPageToLimit(s,"1");var p=this.getTableFacade(s).limit;var q=new c.Sort(o,r,n);p.addSort(q)},removeSortFromLimit:function(q,o){var n=this.getTableFacade(q).limit;var p=n.getSortSet();e.each(p,function(r,s){if(s.property==o){p.splice(r,1);return false}})},removeAllSortsFromLimit:function(o){var n=this.getTableFacade(o).limit;n.setSortSet(new Array());this.setPageToLimit(o,"1")},getSortFromLimit:function(q,o){var n=this.getTableFacade(q).limit;var p=n.getSortSet();e.each(p,function(r,s){if(s.property==o){return s}})},addFilterToLimit:function(r,q,p){this.removeFilterFromLimit(r,q);this.setPageToLimit(r,"1");var n=this.getTableFacade(r).limit;var o=new c.Filter(q,p);n.addFilter(o)},removeFilterFromLimit:function(q,p){var o=this.getTableFacade(q).limit;var n=o.getFilterSet();e.each(n,function(r,s){if(s.property==p){n.splice(r,1);return false}})},removeAllFiltersFromLimit:function(q){var o=this.getTableFacade(q);var n=o.limit;n.setFilterSet(new Array());this.setPageToLimit(q,"1");var p=o.worksheet;p.filter=null},getFilterFromLimit:function(q,p){var o=this.getTableFacade(q).limit;var n=o.getFilterSet();e.each(n,function(r,s){if(s.property==p){return s}})},setExportToLimit:function(o,n){this.getTableFacade(o).limit.setExport(n)},createHiddenInputFieldsForLimit:function(p){var o=this.getTableFacade(p);var n=i(p);o.createHiddenInputFields(n)},createHiddenInputFieldsForLimitAndSubmit:function(q){var o=this.getTableFacade(q);var n=i(q);var p=o.createHiddenInputFields(n);if(p){n.submit()}},createHiddenInputFieldsAllTablesForLimitAndSubmit:function(){var n;var o;e.each(j,function(q,p){if(!n){n=i(q)}o=p.createHiddenInputFields(n)});if(o){n.submit()}},createParameterStringForLimit:function(o){var n=this.getTableFacade(o);return n.createParameterString()},setOnInvokeAction:function(p,o){var n=this.getTableFacade(p);n.onInvokeAction=o},setOnInvokeExportAction:function(p,o){var n=this.getTableFacade(p);n.onInvokeExportAction=o},setContextPath:function(p,n){var o=this.getTableFacade(p);o.contextPath=n},getContextPath:function(o){var n=this.getTableFacade(o);return n.contextPath},onInvokeAction:function(q,p){var o=this.getTableFacade(q);var n=window[o.onInvokeAction];if(e.isFunction(n)!==true){throw o.onInvokeAction+" is not a global function!"}else{n(q,p)}},onInvokeExportAction:function(p){var o=this.getTableFacade(p);var n=window[o.onInvokeExportAction];if(e.isFunction(n)!==true){throw o.onInvokeExportAction+" is not a global function!"}else{n(p)}},getFormByTableId:function(n){return i(n)}};var c={TableFacade:function(n){this.limit=new c.Limit(n);this.worksheet=new c.Worksheet();this.onInvokeAction="onInvokeAction";this.onInvokeExportAction="onInvokeExportAction";this.contextPath=""},Worksheet:function(){this.save=null;this.filter=null;this.clear=null;this.addRow=null;this.removeRow=null},Limit:function(n){this.id=n;this.page=null;this.maxRows=null;this.totalRows=null;this.sortSet=[];this.filterSet=[];this.exportType=null},Sort:function(o,p,n){this.position=o;this.property=p;this.order=n},Filter:function(o,n){this.property=o;this.value=n},DynFilter:function(n,p,o){this.filter=n;this.id=p;this.property=o},WsColumn:function(o,q,n,p){this.column=o;this.id=q;this.uniqueProperties=n;this.property=p}};e.extend(c.Limit.prototype,{getId:function(){return this.id},setId:function(n){this.id=n},getPage:function(){return this.page},setPage:function(n){this.page=n},getMaxRows:function(){return this.maxRows},setMaxRows:function(n){this.maxRows=n},getTotalRows:function(){return this.totalRows},setTotalRows:function(n){this.totalRows=n},getTotalPages:function(){if(this.maxRows==0){return 1}var n=this.totalRows/this.maxRows;if((this.totalRows%this.maxRows)>0){++n}return n},getSortSet:function(){return this.sortSet},addSort:function(n){this.sortSet[this.sortSet.length]=n},setSortSet:function(n){this.sortSet=n},getFilterSet:function(){return this.filterSet},addFilter:function(n){this.filterSet[this.filterSet.length]=n},setFilterSet:function(n){this.filterSet=n},getExport:function(){return this.exportType},setExport:function(n){this.exportType=n}});e.extend(c.TableFacade.prototype,{createHiddenInputFields:function(q){var o=this.limit;var p=e(q).find(":hidden[name="+o.id+"_p_]").val();if(p){return false}if(this.worksheet.save){e(q).append('<input type="hidden" name="'+o.id+'_sw_" value="true"/>')}if(this.worksheet.filter){e(q).append('<input type="hidden" name="'+o.id+'_fw_" value="true"/>')}if(this.worksheet.clear){e(q).append('<input type="hidden" name="'+o.id+'_cw_" value="true"/>')}if(this.worksheet.addRow){e(q).append('<input type="hidden" name="'+o.id+'_awr_" value="true"/>')}if(this.worksheet.removeRow){e.each(this.worksheet.removeRow,function(s,t){e(q).append('<input type="hidden" name="'+o.id+'_rwr_" value="'+t+'"/>')})}e(q).append('<input type="hidden" name="'+o.id+'_tr_" value="true"/>');e(q).append('<input type="hidden" name="'+o.id+'_p_" value="'+o.page+'"/>');e(q).append('<input type="hidden" name="'+o.id+'_mr_" value="'+o.maxRows+'"/>');var r=o.getSortSet();e.each(r,function(s,t){e(q).append('<input type="hidden" name="'+o.id+"_s_"+t.position+"_"+t.property+'" value="'+t.order+'"/>')});var n=o.getFilterSet();e.each(n,function(s,t){e(q).append('<input type="hidden" name="'+o.id+"_f_"+t.property+'" value="'+t.value+'"/>')});return true},createParameterString:function(){var o=this.limit;var p="";p+=o.id+"_p_="+o.page;p+="&"+o.id+"_mr_="+o.maxRows;var q=o.getSortSet();e.each(q,function(r,s){p+="&"+o.id+"_s_"+s.position+"_"+s.property+"="+s.order});var n=o.getFilterSet();e.each(n,function(r,s){p+="&"+o.id+"_f_"+s.property+"="+encodeURIComponent(s.value)});if(o.exportType){p+="&"+o.id+"_e_="+o.exportType}p+="&"+o.id+"_tr_=true";if(this.worksheet.save){p+="&"+o.id+"_sw_=true"}if(this.worksheet.filter){p+="&"+o.id+"_fw_=true"}if(this.worksheet.clear){p+="&"+o.id+"_cw_=true"}if(this.worksheet.addRow){p+="&"+o.id+"_awr_=true"}if(this.worksheet.removeRow){e.each(this.worksheet.removeRow,function(r,s){p+="&"+o.id+"_rwr_="+s})}return p}});var g=null;var f={createDynFilter:function(r,t,s){if(g){return}g=new c.DynFilter(r,t,s);var n=e(r);var q=n.width();var o=n.text();n.width(q);n.parent().width(q);n.css("overflow","visible");n.html('<div id="dynFilterDiv"><input id="dynFilterInput" name="filter" style="width:'+(q+2)+'px" value="" /></div>');var p=e("#dynFilterInput");p.val(o);p.focus();if(jQuery.browser.msie){p.focus()}e(p).keydown(function(u){var w=g.id;if(u.keyCode==13){e.jmesa.saveDynFilterData(n,p,o);e.jmesa.onInvokeAction(w,"filter")}else{if(u.keyCode==9){var v=e.jmesa.findNextCell(w,"dynFilter",u.shiftKey);e.jmesa.saveDynFilterData(n,p,o);if(v!=null){v.onclick();return false}}}});e(p).blur(function(){e.jmesa.saveDynFilterData(n,p,o)})},saveDynFilterData:function(n,p,o){var q=p.val();n.text("");n.css("overflow","hidden");n.text(q);if(o!=q){e.jmesa.addFilterToLimit(g.id,g.property,q)}g=null},createDroplist:function(t,r,w,y){if(e("#"+t).size()>0){return false}w.css("overflow","visible");var v=w.text();var o=w.width();var x=0;e.each(y,function(){x++;if(x>10){x=10;return false}});w.width(o);w.parent().width(o);w.html('<div id="'+t+'" style="top:17px">');var s='<select id="'+r+'" size="'+x+'">';e.each(y,function(z,A){if(z==v){s+='<option selected="selected" value="'+z+'">'+A+"</option>"}else{s+='<option value="'+z+'">'+A+"</option>"}});s+="</select>";var n=e("#"+t);n.html(s);var u=e("#"+r);var q=u.width();if(q<o){u.width(o+5)}u.focus();var p=w.css("backgroundColor");w.css({backgroundColor:n.css("backgroundColor")});return true},createDroplistDynFilter:function(p,o,v,x){if(g){return}g=new c.DynFilter(p,o,v);var w=e(p);var u=w.text();var q=w.css("backgroundColor");var s="dynFilterDroplistDiv";var r="dynFilterDroplist";if(!(e.jmesa.createDroplist(s,r,w,x))){return}var n=e("#"+s);var t=e("#"+r);e(t).click(function(){var y=g.id;e.jmesa.saveDroplistDynFilterData(w,u,q);e.jmesa.onInvokeAction(y,"filter")});e(t).blur(function(){e.jmesa.saveDroplistDynFilterData(w,u,q)});n.keydown(function(y){var A=g.id;if(y.keyCode==13){e.jmesa.saveDroplistDynFilterData(w,u,q);e.jmesa.onInvokeAction(A,"filter")}else{if(y.keyCode==9){var z=e.jmesa.findNextCell(A,"dynFilter",y.shiftKey);e.jmesa.saveDroplistDynFilterData(w,u,q);if(z!=null){z.onclick();return false}}}})},saveDroplistDynFilterData:function(n,o,r){var q=e("#dynFilterDroplistDiv option:selected").val();var p=e("#dynFilterDroplistDiv option:selected").text();n.text(p);n.css("overflow","hidden");if(o!=q){e.jmesa.addFilterToLimit(g.id,g.property,q)}e("#dynFilterDroplistDiv").remove();n.css({backgroundColor:r});g=null}};var h=null;var d;var m={};var b;var k={createWsColumn:function(s,u,q,t){if(h){return}h=new c.WsColumn(s,u,q,t);var n=e(s);var r=n.width();var o=n.text();n.width(r);n.parent().width(r);n.css("overflow","visible");n.html('<div id="wsColumnDiv"><input id="wsColumnInput" name="'+t+'" style="width:'+(r+3)+'px" value=""/></div>');var p=e("#wsColumnInput");p.val(o);p.focus();if(jQuery.browser.msie){p.focus()}this.wsColumnKeyEvent(n,p,o);e("#wsColumnInput").blur(function(){e.jmesa.validateAndSubmitWsColumn(n,p,o)})},createWsAutoCompleteColumn:function(q,o,r,u,n,w){if(h){return}h=new c.WsColumn(q,o,r,u);var v=e(q);var p=v.width();var t=v.text();v.width(p);v.parent().width(p);v.css("overflow","visible");v.html('<div id="wsColumnDiv"><input id="wsColumnInput" name="'+u+'" style="width:'+(p+3)+'px" value=""/></div>');e("input[name="+u+"]").autocomplete(n,w);var s=e("#wsColumnInput");s.val(t);s.focus();if(jQuery.browser.msie){s.focus()}this.wsColumnKeyEvent(v,s,t);e("#wsColumnInput").blur(function(){e.jmesa.validateAndSubmitWsColumn(v,s,t)})},findNextCell:function(r,p,o){var t=null;var q=false;var n=false;var s=null;var u=null;e("#"+r).find("div:[class^="+p+"]").each(function(w,v){if(u==null){u=v}if(q){t=v;return false}else{if(v.style.overflow=="visible"){if(o){if(v==u){n=true}else{t=s;return false}}else{q=true}}}s=v});if(t==null){if(q){t=u}else{if(n){t=s}}}return t},wsColumnKeyEvent:function(n,p,o){var q=function(r){if(r.keyCode==13||r.keyCode==9){var s=null;if(r.keyCode==9){s=e.jmesa.findNextCell(h.id,"wsColumn",r.shiftKey)}e.jmesa.validateAndSubmitWsColumn(n,p,o);if(s!=null){s.onclick();return false}}else{if(r.shiftKey&&r.ctrlKey&&r.keyCode==90){if(n.attr("class")!="wsColumn"){p.val(n.attr("data-ov"))}}}};p.keydown(q)},createWsDroplistColumn:function(q,o,t,w,y){if(h){return}h=new c.WsColumn(q,o,t,w);var x=e(q);var v=x.text();var p=x.css("backgroundColor");var s="wsColumnDroplistDiv";var r="wsColumnDroplistInput";if(!(e.jmesa.createDroplist(s,r,x,y))){return}var n=e("#"+s);var u=e("#"+r);e(u).click(function(){e.jmesa.submitWsDroplistColumn(x,v,p);x.parent().parent().mouseout();return false});e(u).blur(function(){e.jmesa.submitWsDroplistColumn(x,v,p)});n.keydown(function(z){var B=h.id;if(z.keyCode==13||z.keyCode==9){if(z.keyCode==13){e.jmesa.submitWsDroplistColumn(x,v,p);x.parent().parent().mouseout()}else{if(z.keyCode==9){var A=e.jmesa.findNextCell(B,"wsColumn",z.shiftKey);e.jmesa.submitWsDroplistColumn(x,v,p);x.parent().parent().mouseout();if(A!=null){A.onclick();return false}}}}else{if(z.shiftKey&&z.ctrlKey&&z.keyCode==90){if(x.attr("class")!="wsColumn"){u.val(x.attr("data-ov"))}}}})},submitWsDroplistColumn:function(n,o,r){var p=e("#wsColumnDroplistDiv option:selected").val();var q=e("#wsColumnDroplistDiv option:selected").text();n.text(q);n.css("overflow","hidden");if(o!=q){if(n.attr("class")=="wsColumn"){n.attr("data-ov",o)}e.jmesa.submitWsColumn(o,q)}e("#wsColumnDroplistDiv").remove();n.css({backgroundColor:r});h=null},submitWsCheckboxColumn:function(p,t,o,s){h=new c.WsColumn(p,t,o,s);var q=p.checked;var r="unchecked";if(q){r="checked"}var n="unchecked";if(!q){n="checked"}e.jmesa.submitWsColumn(n,r);h=null},setError:function(n){d=n},setValidator:function(o,n){m[o]=n},getValidator:function(o){if(!m[o]){return}var n=e.jmesa.getFormByTableId(o);if(b&&(b!=o)&&(e.jmesa.getFormByTableId(b)==n)){e.data(n,"validator",null)}b=o;return e(n).validate(m[o])},validateAndSubmitWsColumn:function(n,p,o){var s=p.val();var q=e.jmesa.getValidator(h.id);var r;if(q){e.each(p.rules(),function(){r=true})}if(s!=o){if(r){q.element(e("#wsColumnInput"))}}n.text("");n.css("overflow","hidden");n.text(s);if(s!=o){if(n.attr("class")=="wsColumn"){n.attr("data-ov",o)}e.jmesa.submitWsColumn(o,s,r)}h=null},submitWsColumn:function(o,u,s){var t='{ "id" : "'+h.id+'"';t+=', "cp_" : "'+h.property+'"';var r=h.uniqueProperties;e.each(r,function(v,w){t+=', "up_'+v+'" : "'+w+'"'});t+=', "ov_" : "'+encodeURIComponent(o)+'"';t+=', "cv_" : "'+encodeURIComponent(u)+'"';var n=e(h.column);var q;if(s){if(d){q=d[h.property]}}else{q=n.attr("data-em")}if(q){t+=', "em_" : "'+encodeURIComponent(q)+'"'}t+="}";var p=a.getContextPath(h.id);if(p){p+="/"}e.post(p+"jmesa.wrk",jQuery.parseJSON(t),function(v){jQuery.jmesa.updateCssClass(v,n,q)})},updateCssClass:function(q,n,o){var p={_rm_:"wsColumn",_uu_:"wsColumnChange",_ue_:"wsColumnError"};if(n.attr("class").indexOf("wsColumn")==0){n.attr("class",p[q]);if("wsColumn"==p[q]){n.removeAttr("data-ov")}if(o){n.attr("data-em",o)}else{n.removeAttr("data-em")}}},setTitle:function(o,p){var q;var n=e(o);if(p.ctrlKey){q=n.attr("data-ov")}else{q=n.attr("data-em")}if(q){n.attr("title",q)}else{n.removeAttr("title")}}};var l={addDropShadow:function(n,o){if(!o){o="jmesa"}e("div."+o+" .table").wrap("<div class='wrap0'><div class='wrap1'><div class='wrap2'><div class='dropShadow'></div></div></div></div>").css({background:"url("+n+"shadow_back.gif) 100% repeat"});e("."+o+" div.wrap0").css({background:"url("+n+"shadow.gif) right bottom no-repeat","float":"left"});e("."+o+" div.wrap1").css({background:"url("+n+"shadow180.gif) no-repeat"});e("."+o+" div.wrap2").css({background:"url("+n+"corner_bl.gif) -18px 100% no-repeat"});e("."+o+" div.dropShadow").css({background:"url("+n+"corner_tr.gif) 100% -18px no-repeat"});e("div."+o).append('<div style="clear:both">&nbsp;</div>')}};e.extend(a,f);e.extend(a,k);e.extend(a,l);e.jmesa={};e.extend(e.jmesa,a)})(jQuery);
    </script>
    <script type="text/javascript" >
        function addTableFacadeToManager(a){jQuery.jmesa.addTableFacade(a)}function setSaveToWorksheet(a){jQuery.jmesa.setSaveToWorksheet(a)}function setFilterToWorksheet(a){jQuery.jmesa.setFilterToWorksheet(a)}function removeFilterFromWorksheet(a){jQuery.jmesa.removeFilterFromWorksheet(a)}function setPageToLimit(b,a){jQuery.jmesa.setPageToLimit(b,a)}function setMaxRowsToLimit(b,a){jQuery.jmesa.setMaxRowsToLimit(b,a)}function addSortToLimit(d,b,c,a){jQuery.jmesa.addSortToLimit(d,b,c,a)}function removeSortFromLimit(b,a){jQuery.jmesa.removeSortFromLimit(b,a)}function removeAllSortsFromLimit(a){jQuery.jmesa.removeAllSortsFromLimit(a)}function getSortFromLimit(b,a){jQuery.jmesa.getSortFromLimit(b,a)}function addFilterToLimit(b,a){jQuery.jmesa.addFilterToLimit(b,a)}function removeFilterFromLimit(b,a){jQuery.jmesa.removeFilterFromLimit(b,a)}function removeAllFiltersFromLimit(a){jQuery.jmesa.removeAllFiltersFromLimit(a)}function getFilterFromLimit(b,a){jQuery.jmesa.getFilterFromLimit(b,a)}function setExportToLimit(b,a){jQuery.jmesa.setExportToLimit(b,a)}function createHiddenInputFieldsForLimit(a){jQuery.jmesa.createHiddenInputFieldsForLimit(a)}function createHiddenInputFieldsForLimitAndSubmit(a){jQuery.jmesa.createHiddenInputFieldsForLimitAndSubmit(a)}function createParameterStringForLimit(a){return jQuery.jmesa.createParameterStringForLimit(a)}function createDynFilter(a,c,b){jQuery.jmesa.createDynFilter(a,c,b)}function createDynDroplistFilter(b,d,c,a){jQuery.jmesa.createDroplistDynFilter(b,d,c,a)}function createWsColumn(b,d,a,c){jQuery.jmesa.createWsColumn(b,d,a,c)}function submitWsCheckboxColumn(b,d,a,c){jQuery.jmesa.submitWsCheckboxColumn(b,d,a,c)}function submitWsColumn(a,b){jQuery.jmesa.submitWsColumn(a,b)}function addDropShadow(a,b){jQuery.jmesa.addDropShadow(a,b)};
    </script>

    <script type="text/javascript">
        function onInvokeAction(id) {
            $.jmesa.setExportToLimit(id, '');

            $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
        }

    </script>
    <div class="title">Games<%--${game.gameName} khelechen ?--%>
    </div>
    <%--<div class="entry">--%>

    <form name="GameForm" action="${pageContext.request.contextPath}/Games.htm">


        <jmesa:tableModel id="gamesTag" items="${gameList}" var="game" maxRows="30" maxRowsIncrements="30">

            <c:url value="/Game.htm" var="displayURL">
                <c:param name="gameId" value="${game.gameId}"/>
            </c:url>

            <jmesa:htmlTable width="800px" styleClass="jmesa" cellpadding="15px" cellspacing="5px">
                <jmesa:htmlRow>
                    <jmesa:htmlColumn property="gameName">
                        <a href="${displayURL}">${game.gameName}</a>
                    </jmesa:htmlColumn>

                    <jmesa:htmlColumn property="developer"/>
                    <jmesa:htmlColumn property="platform"/>
                    <jmesa:htmlColumn property="genreString" title="Genres(s)"/>
                    <jmesa:htmlColumn title="Rating">
                        <%--${(game.ratingPresentation+game.ratingGraphics+game.ratingGamePlay+game.ratingSound+game.ratingLongevity)/5}--%>
                        ${game.ratingOverall}
                    </jmesa:htmlColumn>
                </jmesa:htmlRow>
            </jmesa:htmlTable>
        </jmesa:tableModel>
    </form>


    <%--</div>--%>
</div>


</body>
</html>